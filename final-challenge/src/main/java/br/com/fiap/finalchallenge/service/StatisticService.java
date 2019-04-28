package br.com.fiap.finalchallenge.service;

import java.util.List;

import br.com.fiap.finalchallenge.exception.TransactionExpiredException;
import br.com.fiap.finalchallenge.exception.TransactionOutOfFutureWindow;
import br.com.fiap.finalchallenge.model.Statistic;
import br.com.fiap.finalchallenge.model.Transaction;


public interface StatisticService {
	
	public List<Statistic> getAllStatistics();
	public Statistic getStatistic();
	
	public void add(Transaction transaction) throws TransactionExpiredException, TransactionOutOfFutureWindow;
	
}
