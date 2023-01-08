package com.btt.pay.repository;

import com.btt.pay.domain.Account;
import com.btt.pay.domain.enumeration.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Boolean existsByAccountType(AccountType accountType);
    Optional<Account> findByAccountNumber(String accountNumber);

    @Query("SELECT accountNumber from Account account WHERE account.accountType = :accountType ORDER BY accountNumber DESC LIMIT 1")
    String findRecentAccountNumber(@Param("accountType") AccountType accountType);

    @Query("SELECT account FROM Account account WHERE account.user.id = :userId ORDER BY account.accountNumber")
    Optional<List<Account>> findByUserId(@Param("userId") Long userId);

    @Query("SELECT account FROM Account account WHERE account.accountType = :accountType AND account.user.id = :userId")
    Optional<Account> findByAccountTypeAndUserId(@Param("accountType") AccountType accountType, @Param("userId") Long userId);

}
