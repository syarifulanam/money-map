package com.syarifulanam.moneymap.service;

import com.syarifulanam.moneymap.repository.TransactionRepository;
import com.syarifulanam.moneymap.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BalanceService balanceService;

    public Transaction saveTransaction(Transaction transaction, Long balancesId) {
        transaction.setUser(userService.getLoggedInUser());
        transaction.setBalance(balanceService.getBalanceById(balancesId));
        return transactionRepository.save(transaction);
    }

    // getAllTransaction untuk user token  yang login aja
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    // getTransactionById untuk user token  yang login aja
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
        existingTransaction.setNotes(transaction.getNotes());

        return transactionRepository.save(existingTransaction);
    }

    public void deleteTransaction(Long transactionId) {
        Transaction existingTransaction = getTransactionById(transactionId);
        transactionRepository.deleteById(existingTransaction.getId());
    }
}
