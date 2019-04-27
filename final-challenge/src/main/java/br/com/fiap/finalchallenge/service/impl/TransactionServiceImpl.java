package br.com.fiap.finalchallenge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.finalchallenge.json.TransactionPostJson;
import br.com.fiap.finalchallenge.model.Transaction;
import br.com.fiap.finalchallenge.reprository.TransactionRepository;
import br.com.fiap.finalchallenge.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public void saveTransaction(TransactionPostJson bodyJson) {
		transactionRepository.saveTransaction(bodyJson);
	}
	
	public Transaction getCurrentTransaction() {
		return transactionRepository.getCurrentTransaction();
	}

}
