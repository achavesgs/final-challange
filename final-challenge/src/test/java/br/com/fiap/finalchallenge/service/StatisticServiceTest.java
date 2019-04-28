package br.com.fiap.finalchallenge.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.fiap.finalchallenge.exception.TransactionExpiredException;
import br.com.fiap.finalchallenge.exception.TransactionOutOfFutureWindow;
import br.com.fiap.finalchallenge.model.Statistic;
import br.com.fiap.finalchallenge.model.Transaction;
import br.com.fiap.finalchallenge.util.Util;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StatisticServiceTest {

	@Autowired
	private StatisticService statisticService;

	@Value("${statisticService.windowInMs}")
	private Long windowInMs;

	SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

	@Test(expected = TransactionExpiredException.class)
	public void addExpired() throws TransactionExpiredException, TransactionOutOfFutureWindow {

		Transaction transaction = new Transaction();
		transaction.setAmount(5.0);
		transaction.setDate(LocalDateTime.now().minusSeconds(windowInMs / 1000).minusSeconds(1l));
		transaction.setTimestamp(Util.converToTimeStamp(transaction.getDate()));

		this.statisticService.add(transaction);
	}

	@Test(expected = TransactionOutOfFutureWindow.class)
	public void addOutOfFutureWindow() throws TransactionExpiredException, TransactionOutOfFutureWindow {

		Transaction transaction = new Transaction();
		transaction.setAmount(5.0);
		transaction.setDate(LocalDateTime.now().plusSeconds(windowInMs / 1000).plusSeconds(1l));
		transaction.setTimestamp(Util.converToTimeStamp(transaction.getDate()));
		this.statisticService.add(transaction);
	}

	@Test
	public void findCurrentEmpty() {

		Long currentTimestamp = Util.converToTimeStamp(LocalDateTime.now());
		LocalDateTime currentDate = Util.convertToLocalDateTime(currentTimestamp);

		Statistic statistic = this.statisticService.getStatistic();
		Assert.assertEquals(currentDate, statistic.getDate());
	}

	@Test
	public void findCurrent() throws TransactionExpiredException, TransactionOutOfFutureWindow {

		Transaction tr1 = new Transaction();
		tr1.setAmount(5.0);
		tr1.setDate(LocalDateTime.now());
		tr1.setTimestamp(Util.converToTimeStamp(tr1.getDate()));
		this.statisticService.add(tr1);

		Transaction tr2 = new Transaction();
		tr2.setAmount(10.0);
		tr2.setDate(LocalDateTime.now());
		tr2.setTimestamp(Util.converToTimeStamp(tr2.getDate()));
		this.statisticService.add(tr2);

		Transaction tr3 = new Transaction();
		tr3.setAmount(20.0);
		tr3.setDate(LocalDateTime.now());
		tr3.setTimestamp(Util.converToTimeStamp(tr3.getDate()));
		this.statisticService.add(tr3);

		Transaction tr4 = new Transaction();
		tr4.setAmount(50.0);
		tr4.setDate(LocalDateTime.now());
		tr4.setTimestamp(Util.converToTimeStamp(tr4.getDate()));
		this.statisticService.add(tr4);

		Long currentTimestamp = Util.converToTimeStamp(LocalDateTime.now());
		LocalDateTime currentDate = Util.convertToLocalDateTime(currentTimestamp);

		Statistic statistic = this.statisticService.getStatistic();
		Assert.assertEquals(currentDate, statistic.getDate());
		Assert.assertEquals(currentDate, statistic.getDate());
		Assert.assertEquals(Long.valueOf(4l).toString(), statistic.getCount());
		Assert.assertEquals(Double.valueOf(5.0).toString(), statistic.getMin());
		Assert.assertEquals(Double.valueOf(50.0).toString(), statistic.getMax());
		Assert.assertEquals(Double.valueOf(85.0).toString(), statistic.getSum());
		Assert.assertEquals(Double.valueOf(21.25).toString(), statistic.getAvg());
	}

}
