package br.com.fiap.finalchallenge.service;

import br.com.fiap.finalchallenge.json.TransactionPostJson;
import br.com.fiap.finalchallenge.model.Transaction;

public interface TransactionService {
	
	void saveTransaction(TransactionPostJson bodyJson);
	
	Transaction getCurrentTransaction();

}
