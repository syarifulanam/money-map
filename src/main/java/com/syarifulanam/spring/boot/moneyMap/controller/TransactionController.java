package com.syarifulanam.spring.boot.moneyMap.controller;

import com.syarifulanam.spring.boot.moneyMap.entity.Transaction;
import com.syarifulanam.spring.boot.moneyMap.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> saveTransaction(@RequestBody Transaction transaction) {
        return new ResponseEntity<>(transactionService.saveTransaction(transaction), HttpStatus.CREATED);
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransaction() {
        return transactionService.getAllTransaction();
    }

    @GetMapping("/transactions/{id}")
    public Transaction getTransactionById(@PathVariable(name = "id") Long id) {
        return transactionService.getTransactionById(id);
    }

    @PatchMapping("/transactions/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable(name = "id") long id, @RequestBody Transaction transaction) {
        Transaction updateTransaction = transactionService.updatedTransaction(id, transaction);
        return new ResponseEntity<>(updateTransaction, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/transactions/{id}")
    public void deleteTransaction(@PathVariable(name = "id") long id) {
        transactionService.deleteTransaction(id);
    }
}
