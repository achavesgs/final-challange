package br.com.fiap.finalchallenge.reprository;

import java.util.List;

import br.com.fiap.finalchallenge.json.TransactionPostJson;
import br.com.fiap.finalchallenge.model.Transaction;

public interface TransactionRepository {
	void saveTransaction(TransactionPostJson bodyJson);
	
	Transaction getCurrentTransaction();
}
