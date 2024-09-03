package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public void creatAccount(Integer authID, Account account) {
        Customer customer = customerRepository.findCustomerById(authID);
        Account newAccount = new Account();
        newAccount.setAccountNumber(account.getAccountNumber());
        newAccount.setBalance(account.getBalance());
        newAccount.setActive(account.isActive());
        newAccount.setCustomer(customer);
        accountRepository.save(newAccount);
    }

    public void activateAccount(Integer authID, Integer accountID) throws ApiException {
        Customer customer = customerRepository.findCustomerById(authID);
        Account account = accountRepository.findAccountsByIdAndCustomerId(accountID, authID);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if (account.getCustomer().getId() != customer.getId()) {
            throw new ApiException("It's not your account");
        }
        account.setActive(true);
        accountRepository.save(account);
    }

    public void deactivateAccount(Integer accountID) throws ApiException {
        Account account = accountRepository.findAccountById(accountID);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        account.setActive(false);
        accountRepository.save(account);
    }

    public Account getAccount(Integer authID, int accountId) throws ApiException {
        Account account = accountRepository.findAccountsByIdAndCustomerId(accountId, authID);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        return account;
    }

    public List<Account> getAccounts(Integer authID) throws ApiException {
        return accountRepository.findAllByCustomerId(authID);
    }

    public void deposit(Integer authID, Integer accountID, Integer amount) throws ApiException {
        Account account = accountRepository.findAccountsByIdAndCustomerId(accountID, authID);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if (!account.isActive()){
            throw new ApiException("Account is not active");
        }
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }
    public void withdraw(Integer authID, Integer accountID, Integer amount) throws ApiException {
        Account account = accountRepository.findAccountsByIdAndCustomerId(accountID, authID);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if (!account.isActive()){
            throw new ApiException("Account is not active");
        }
        if (account.getBalance() < amount){
            throw new ApiException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    public void transfer(Integer customerID, Integer fromAccountID, Integer toAccountID, Integer amount) throws ApiException {
        Account fromAccount = accountRepository.findAccountsByIdAndCustomerId(fromAccountID, customerID);
        Account toAccount = accountRepository.findAccountById(toAccountID);
        if (fromAccount == null || toAccount == null) {
            throw new ApiException("Account not found");
        }
        if (!fromAccount.isActive() || !toAccount.isActive()){
            throw new ApiException("Account is not active");
        }
        if (fromAccount.getBalance() < amount) {
            throw new ApiException("Insufficient balance");
        }
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }
}
