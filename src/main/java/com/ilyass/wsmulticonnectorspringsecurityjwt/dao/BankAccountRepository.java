package com.ilyass.wsmulticonnectorspringsecurityjwt.dao;

import com.ilyass.wsmulticonnectorspringsecurityjwt.service.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    Optional<BankAccount> findByRib(String rib);
}
