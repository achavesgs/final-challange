package br.com.fiap.finalchallenge.reprository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.fiap.finalchallenge.model.Transaction;
import br.com.fiap.finalchallenge.reprository.TransactionRepository;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository{
	
	private List<Transaction> transactions = new ArrayList<Transaction>();

	public void saveTransaction(Transaction transaction) {
		transactions.add(transaction);
	}
}
