package com.ilyass.wsmulticonnectorspringsecurityjwt.dao;

import com.ilyass.wsmulticonnectorspringsecurityjwt.service.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class BankAccountRepository extends JpaRepository<BankAccount, Long> {
    Optional<BankAccount> findByRib(String rib);
}
