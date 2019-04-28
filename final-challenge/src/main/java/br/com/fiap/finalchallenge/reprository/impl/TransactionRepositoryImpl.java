package br.com.fiap.finalchallenge.reprository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.fiap.finalchallenge.json.TransactionPostJson;
import br.com.fiap.finalchallenge.model.Transaction;
import br.com.fiap.finalchallenge.reprository.TransactionRepository;
import br.com.fiap.finalchallenge.util.Util;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository{
	
	private Transaction transaction = new Transaction();
	
	@Override
	public void saveTransaction(TransactionPostJson bodyJson) {
		transaction.setAmount(bodyJson.getAmount());
		transaction.setTimestamp(bodyJson.getTimestamp());
		transaction.setDate(Util.convertToLocalDateTime(bodyJson.getTimestamp()));
	}


	@Override
	public Transaction getCurrentTransaction() {
		return this.transaction;
	}
}
