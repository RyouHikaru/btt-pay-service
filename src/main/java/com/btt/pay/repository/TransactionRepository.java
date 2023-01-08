package com.btt.pay.repository;

import com.btt.pay.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT transaction FROM Transaction transaction WHERE transaction.account.accountNumber = :accountNumber")
    Optional<List<Transaction>> findByAccountNumber(@Param("accountNumber") String accountNumber);

    @Query("SELECT MAX(transaction.transactionNumber) from Transaction transaction")
    String findRecentTransactionNumber();

}
