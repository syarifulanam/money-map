package com.syarifulanam.moneymap.controller;

import com.syarifulanam.moneymap.entity.Balance;
import com.syarifulanam.moneymap.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @PostMapping("/balances")
    public ResponseEntity<Balance> saveBalance(@RequestBody Balance balance) {
        // CARA 1
        /*Balance saveBalance = balanceService.saveBalance(balance);
        return new ResponseEntity<>(saveBalance, HttpStatus.CREATED);*/

        // CARA 2
        return new ResponseEntity<>(balanceService.saveBalance(balance), HttpStatus.CREATED);
    }

    @GetMapping("/balances")
    public List<Balance> getAllBalance() {
        return balanceService.getAllBalance();
    }

    @GetMapping("/balances/{id}")
    public Balance getBalanceById(@PathVariable(name = "id") long id) {
        return balanceService.getBalanceById(id);
    }

    @PatchMapping("/balances/{id}")
    public ResponseEntity<Balance> updateBalance(@PathVariable(name = "id") long id, @RequestBody Balance balance) {
        Balance updateBalance = balanceService.updateBalance(id, balance);
        return new ResponseEntity<>(updateBalance, HttpStatus.OK);
    }

    // CARA 1
    /*@DeleteMapping("/balances/{id}")
    public ResponseEntity<Void> deleteBalance(@PathVariable(name = "id") long id) {
        balanceService.deleteBalance(id);
        return ResponseEntity.noContent().build();
    }*/

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/balances/{id}")
    public void deleteBalance(@PathVariable(name = "id") long id) {
        balanceService.deleteBalance(id);
    }
}

