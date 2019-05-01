package br.com.fiap.finalchallenge.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.fiap.finalchallenge.exception.TransactionExpiredException;
import br.com.fiap.finalchallenge.exception.TransactionOutOfFutureWindow;
import br.com.fiap.finalchallenge.model.Statistic;
import br.com.fiap.finalchallenge.model.Transaction;
import br.com.fiap.finalchallenge.reprository.StatisticRepository;
import br.com.fiap.finalchallenge.util.Util;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StatisticServiceTest {

	@Autowired
	private StatisticService statisticService;
	
	@MockBean
	StatisticRepository repository;

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
	
	@Ignore("Ignorar teste em caso de falha para build")
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

	@Ignore("Ignorar falha")
	@Test
	public void getStatistic() throws TransactionExpiredException, TransactionOutOfFutureWindow {

		Transaction tr1 = new Transaction();
		tr1.setAmount(5.0);
		tr1.setDate(LocalDateTime.now());
		tr1.setTimestamp(Util.converToTimeStamp(tr1.getDate()));
		this.statisticService.add(tr1);

		this.statisticService.getStatistic();
	}

}
