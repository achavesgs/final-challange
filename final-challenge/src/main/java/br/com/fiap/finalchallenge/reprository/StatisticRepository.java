package br.com.fiap.finalchallenge.reprository;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.fiap.finalchallenge.model.Statistic;
import br.com.fiap.finalchallenge.model.Transaction;

public interface StatisticRepository {
	
	ResponseEntity<Statistic> getStatistic(List<Transaction> transactions);

}
