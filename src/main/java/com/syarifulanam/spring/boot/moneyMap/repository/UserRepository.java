package com.syarifulanam.spring.boot.moneyMap.repository;

import com.syarifulanam.spring.boot.moneyMap.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
