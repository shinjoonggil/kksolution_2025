package com.kks.kksolution.repository;

import com.kks.kksolution.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {
    Optional<UserAccount> findByAccountId(String accountId);
}
