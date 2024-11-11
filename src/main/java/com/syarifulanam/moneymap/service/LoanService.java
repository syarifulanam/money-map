package com.syarifulanam.moneymap.service;

import com.syarifulanam.moneymap.repository.LoanRepository;
import com.syarifulanam.moneymap.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public List<Loan> getAllLoan() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(Long loanId) {
        Optional<Loan> existingLoan = loanRepository.findById(loanId);
        if (existingLoan.isPresent()) {
            return existingLoan.get();
        }
        throw new RuntimeException("Data tidak ditemukan");
    }

    public Loan updatedLoan(Long loanId, Loan loan) {
        Loan existingLoan = getLoanById(loanId);

        existingLoan.setName(loan.getName());
        existingLoan.setAmount(loan.getAmount());
        existingLoan.setType(loan.getType());
        existingLoan.setStatus(loan.getStatus());
        existingLoan.setBalances_id(loan.getBalances_id());
        existingLoan.setUser_id(loan.getUser_id());
        existingLoan.setNotes(loan.getNotes());

        return loanRepository.save(existingLoan);
    }

    public void deleteLoan(Long loanId) {
        Loan existingLoan = getLoanById(loanId);
        loanRepository.deleteById(existingLoan.getId());
    }
}
