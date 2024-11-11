package com.syarifulanam.moneymap.repository;

import com.syarifulanam.moneymap.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

    Optional<Balance> findByName(String name);

    List<Balance> findByUserId(long userId);

    Optional<Balance> findByIdAndUserId(long id, long userId);

}
