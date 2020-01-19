package com.piper2.momo.database.repositories;

import com.piper2.momo.database.models.core.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByAccountNumber(long accountNumber);
}
