package com.syarifulanam.moneymap.controller;

import com.syarifulanam.moneymap.entity.Loan;
import com.syarifulanam.moneymap.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/loans")
    public ResponseEntity<Loan> saveLoan(@RequestBody Loan loan) {
        return new ResponseEntity<>(loanService.saveLoan(loan), HttpStatus.CREATED);
    }

    @GetMapping("/loans")
    public List<Loan> getAllLoan() {
        return loanService.getAllLoan();
    }

    @GetMapping("/loans/{id}")
    public Loan getLoanById(@PathVariable(name = "id") Long id) {
        return loanService.getLoanById(id);
    }

    @PatchMapping("/loans/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable(name = "id") long id, @RequestBody Loan loan) {
        Loan updateLoan = loanService.updatedLoan(id, loan);
        return new ResponseEntity<>(updateLoan, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/loans/{id}")
    public void deleteLoan(@PathVariable(name = "id") long id) {
        loanService.deleteLoan(id);
    }
}
