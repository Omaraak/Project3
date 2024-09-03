package com.example.project3.Repository;

import com.example.project3.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findAccountById(int accountId);
    List<Account> findAllByCustomerId(int customerId);
    Account findAccountsByIdAndCustomerId(int accountId, int customerId);
}
