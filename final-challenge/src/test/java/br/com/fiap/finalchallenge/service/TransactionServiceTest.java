package br.com.fiap.finalchallenge.service;

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
import br.com.fiap.finalchallenge.json.TransactionPostJson;
import br.com.fiap.finalchallenge.model.Transaction;
import br.com.fiap.finalchallenge.util.Util;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TransactionServiceTest {
	
	@Autowired
	private TransactionService transactionService;
	
	@Value("${statisticService.windowInMs}")
	private Long windowInMs;
	
	@Test(expected = TransactionExpiredException.class)
	public void processExpired() throws TransactionExpiredException, TransactionOutOfFutureWindow {
		
		TransactionPostJson json = new TransactionPostJson();
		json.setAmount(6.0);
		json.setTimestamp(Util.converToTimeStamp(LocalDateTime.now()) - windowInMs - 1000);
		
		this.transactionService.saveTransaction(json);
	}
	
	@Test(expected = TransactionOutOfFutureWindow.class)
	public void processFuture() throws TransactionExpiredException, TransactionOutOfFutureWindow {
		
		TransactionPostJson json = new TransactionPostJson();
		json.setAmount(6.0);
		json.setTimestamp(Util.converToTimeStamp(LocalDateTime.now()) + windowInMs + 1000);
		
		this.transactionService.saveTransaction(json);
	}
	
	@Test
	public void processOk() throws TransactionExpiredException, TransactionOutOfFutureWindow {
		
		TransactionPostJson json = new TransactionPostJson();
		json.setAmount(6.0);
		json.setTimestamp(Util.converToTimeStamp(LocalDateTime.now()) - windowInMs);
		
		this.transactionService.saveTransaction(json);
		
		Transaction transaction = this.transactionService.getCurrentTransaction();
		
//		Assert.assertEquals(json.getAmount(), transaction.getAmount());
		Assert.assertEquals(Util.convertToLocalDateTime(json.getTimestamp()), transaction.getDate());
	}

}
