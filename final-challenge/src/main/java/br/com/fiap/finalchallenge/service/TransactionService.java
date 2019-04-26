package br.com.fiap.finalchallenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.finalchallenge.json.TransactionPostJson;
import br.com.fiap.finalchallenge.model.Transaction;
import br.com.fiap.finalchallenge.reprository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public List<Transaction> saveTransaction(TransactionPostJson bodyJson) {
		return transactionRepository.saveTransaction(bodyJson);
	}

}
