package com.syarifulanam.spring.boot.moneyMap.repository;

import com.syarifulanam.spring.boot.moneyMap.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // select * from users where email = anam@mmm.com
    Boolean existsByEmail(String email);

    // select * from users where email = anam@mmm.com
    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);
}
