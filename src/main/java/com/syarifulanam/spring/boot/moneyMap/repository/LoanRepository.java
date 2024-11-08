package com.syarifulanam.spring.boot.moneyMap.repository;

import com.syarifulanam.spring.boot.moneyMap.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
}
