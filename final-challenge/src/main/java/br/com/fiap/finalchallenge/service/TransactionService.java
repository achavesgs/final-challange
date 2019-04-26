package br.com.fiap.finalchallenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.finalchallenge.model.Transaction;
import br.com.fiap.finalchallenge.reprository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public void saveTransaction(Transaction transaction) {
		transactionRepository.saveTransaction(transaction);
	}

}
