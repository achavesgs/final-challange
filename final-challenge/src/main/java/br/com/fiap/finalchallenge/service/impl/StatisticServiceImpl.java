package br.com.fiap.finalchallenge.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.fiap.finalchallenge.exception.TransactionExpiredException;
import br.com.fiap.finalchallenge.exception.TransactionOutOfFutureWindow;
import br.com.fiap.finalchallenge.model.Statistic;
import br.com.fiap.finalchallenge.model.Transaction;
import br.com.fiap.finalchallenge.service.StatisticService;
import br.com.fiap.finalchallenge.util.Util;

/**
 * 
 * Statistics Service
 * 
 * @author 
 *
 */
@Service
public class StatisticServiceImpl implements StatisticService {

	// Lock for ADD / REMOVE
	private Object LOCK = new Object();
	
	// Space Complexity: O(${windowInMs} / 1000 + ${removeExpiredStatisticsInMs} / 1000) -> O(1)
	private Map<Long, Statistic> statisticHistory;

	// Space Complexity: O(${windowInMs} / 1000 + ${removeExpiredStatisticsInMs} / 1000) -> O(1)
	private Queue<Long> statisticTimestamps;
	
	@Value("${statisticService.windowInMs}")
	private Long windowInMs;
	
	public StatisticServiceImpl() {
		 this.statisticHistory = new ConcurrentHashMap<Long, Statistic>();
		 this.statisticTimestamps = new PriorityBlockingQueue<Long>();
	}
	
	/**
	 * 
	 * Create init statistic
	 * 
	 * Time complexity O(1)
	 * 
	 * @return Statistic with init values
	 */
	private Statistic createInitStatistic(Long timestamp) {
		Statistic statistic = new Statistic();
		statistic.setDate(Util.convertToLocalDateTime(timestamp));
		statistic.setMax(Double.MIN_VALUE);
		statistic.setMin(Double.MAX_VALUE);
		statistic.setSum(0.0);
		statistic.setCount(0l);
		return statistic;
	}
	
	/**
	 * 
	 * Remove expired statistics
	 * 
	 * Time complexity: O(statisticTimestamps.size() * log statisticTimestamps.size()) -> O(1)
	 * 
	 */
	@Scheduled(fixedDelayString = "${statisticService.removeExpiredStatisticsInMs}")
	private void removeExpiredStatistics() {

		Long currentTimestamp = Util.converToTimeStamp(LocalDateTime.now());
		
		if (this.statisticTimestamps.isEmpty() || this.statisticTimestamps.peek() >= currentTimestamp) return;
		
		synchronized(LOCK) {
		
			// O(n) - Where n = statisticTimestamps.size()
			while(!this.statisticTimestamps.isEmpty() && this.statisticTimestamps.peek() < currentTimestamp) {
				
				// O(log n) - Where n = statisticTimestamps.size()
				Long key = this.statisticTimestamps.poll();
				
				// O(1)
				this.statisticHistory.remove(key);
			}
		}
	}
	
	/**
	 * 
	 * Add statistic from transaction
	 * 
	 * Time complexity: O(${windowInMs}/1000 * log statisticTimestamps.size()) -> O(1)
	 * @throws TransactionOutOfFutureWindow 
	 * 
	 */
	@Override
	public void add(Transaction transaction) throws TransactionExpiredException, TransactionOutOfFutureWindow {
		
		Long currentTimestamp = Util.converToTimeStamp(LocalDateTime.now());
		Long transactionTimestamp = Util.converToTimeStamp(transaction.getDate());
		
		if (transactionTimestamp + windowInMs < currentTimestamp) throw new TransactionExpiredException();
		if (currentTimestamp + windowInMs < transactionTimestamp) throw new TransactionOutOfFutureWindow();
		
		synchronized(LOCK) {
			
			// O(n) - Where n = ${windowInMs}/1000
			for(Long i = currentTimestamp; i < transactionTimestamp + windowInMs; i+=1000) {
				
				// O(1)
				Statistic statistic = this.statisticHistory.get(i);
				
				if (statistic == null) {
					
					// O(1)
					statistic = this.createInitStatistic(i);
				
					// O(1)
					this.statisticHistory.put(i, statistic);
					
					// O(log n) - Where n = statisticTimestamps.size()
					this.statisticTimestamps.add(i);
				}
	
				if (transaction.getAmount() > statistic.getMax()) statistic.setMax(transaction.getAmount());
				if (transaction.getAmount() < statistic.getMin()) statistic.setMin(transaction.getAmount());
				
				statistic.setSum( statistic.getSum() + transaction.getAmount() );
				statistic.setCount( statistic.getCount() + 1);
				statistic.setAvg( statistic.getSum() / statistic.getCount() );
			}
		
		}
	}
	
	/**
	 * 
	 * Get current statistic
	 * 
	 * Time complexity: O(1)
	 * 
	 */
	@Override
	public Statistic findCurrent() {
		
		Long currentTimestamp = Util.converToTimeStamp(LocalDateTime.now());
		
		// O(1)
		Statistic statistic = this.statisticHistory.get(currentTimestamp);
		
		if (statistic == null) statistic = this.createInitStatistic(currentTimestamp);
		
		return statistic;
	}
	
	
	/**
	 * 
	 * Get all statistics
	 * 
	 * Time complexity: O(statisticHistory.values().size() log statisticHistory.values().size()) -> O(1)
	 * 
	 */
	@Override
	public List<Statistic> findAll() {
		
		List<Statistic> result = new ArrayList<Statistic>(this.statisticHistory.values());
		
		// O(n log  n) - Where n = statisticHistory.values().size()
		result.sort((Statistic o1, Statistic o2)->o1.getDate().compareTo(o2.getDate()));
		
		return result;
	}
		
}