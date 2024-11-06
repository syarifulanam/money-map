package com.syarifulanam.spring.boot.moneyMap.repository;

import com.syarifulanam.spring.boot.moneyMap.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

    Optional<Balance> findByName(String name);

    List<Balance> findByUserId(long id);

}
