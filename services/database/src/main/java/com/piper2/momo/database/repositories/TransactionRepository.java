package com.piper2.momo.database.repositories;

import com.piper2.momo.database.models.core.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Optional<List<Transaction>> findAllByFromAccountOrToAccount(long fromAccount, long toAccount);
}
