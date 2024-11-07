package com.syarifulanam.spring.boot.moneyMap.service;

import com.syarifulanam.spring.boot.moneyMap.entity.Transaction;
import com.syarifulanam.spring.boot.moneyMap.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long transactionId) {
        Optional<Transaction> existingTransaction = transactionRepository.findById(transactionId);
        if (existingTransaction.isPresent()) {
            return existingTransaction.get();
        }
        throw new RuntimeException("Data tidak ditemukan");
    }

    public Transaction updatedTransaction(Long transactionId, Transaction transaction) {
        Transaction existingTransaction = getTransactionById(transactionId);

        existingTransaction.setName(transaction.getName());
        existingTransaction.setType(transaction.getType());
        existingTransaction.setAmount(transaction.getAmount());
        existingTransaction.setBalances_id(transaction.getBalances_id());
        existingTransaction.setUser_id(transaction.getUser_id());
        existingTransaction.setNotes(transaction.getNotes());

        return transactionRepository.save(existingTransaction);
    }

    public void deleteTransaction(Long transactionId) {
        Transaction existingTransaction = getTransactionById(transactionId);
        transactionRepository.deleteById(existingTransaction.getId());
    }
}
