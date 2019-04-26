package br.com.fiap.finalchallenge.reprository;

import br.com.fiap.finalchallenge.model.Transaction;

public interface TransactionRepository {
	public void saveTransaction(Transaction transaction);
}
