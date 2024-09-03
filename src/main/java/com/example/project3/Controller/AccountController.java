package com.example.project3.Controller;

import com.example.project3.Api.ApiException;
import com.example.project3.Model.Account;
import com.example.project3.Model.User;
import com.example.project3.Service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/creatAccount")
    public ResponseEntity createAccount(@AuthenticationPrincipal User user, @RequestBody Account account) {
        accountService.creatAccount(user.getId(), account);
        return ResponseEntity.status(200).body("Account created");
    }

    @PutMapping("/activateAccount/{accountID}")
    public ResponseEntity activateAccount(@AuthenticationPrincipal User user, @PathVariable int accountID) throws ApiException {
        accountService.activateAccount(user.getId(), accountID);
        return ResponseEntity.status(200).body(user.getUsername()+" account is activated");
    }

    @PutMapping("/deactivateAccount/{accountID}")
    public ResponseEntity deactivateAccount(@AuthenticationPrincipal User user, @PathVariable int accountID) throws ApiException {
        accountService.deactivateAccount(accountID);
        return ResponseEntity.status(200).body(user.getUsername()+" account is deactivateAccounted");
    }

    @GetMapping("/getAccount/{accountId}")
    public ResponseEntity getAccount(@AuthenticationPrincipal User user, @PathVariable int accountId) throws ApiException {
        return ResponseEntity.status(200).body(accountService.getAccount(user.getId(), accountId));
    }

    @GetMapping("/getAccounts")
    public ResponseEntity getAccounts(@AuthenticationPrincipal User user) throws ApiException {
        return ResponseEntity.status(200).body(accountService.getAccounts(user.getId()));
    }

    @PutMapping("/deposit/{accountID}/{amount}")
    public ResponseEntity deposit(@AuthenticationPrincipal User user, @PathVariable int accountID, @PathVariable int amount) throws ApiException {
        accountService.deposit(user.getId(), accountID, amount);
        return ResponseEntity.status(200).body(user.getUsername()+" account deposit successfully");
    }

    @PutMapping("/withdraw/{accountID}/{amount}")
    public ResponseEntity withdraw(@AuthenticationPrincipal User user, @PathVariable int accountID, @PathVariable int amount) throws ApiException {
        accountService.withdraw(user.getId(), accountID, amount);
        return ResponseEntity.status(200).body(user.getUsername()+" account withdraw successfully");
    }

    @PutMapping("/transfer/{fromAccountID}/{toAccountID}/{amount}")
    public ResponseEntity transfer(@AuthenticationPrincipal User user, @PathVariable int fromAccountID, @PathVariable int toAccountID, @PathVariable int amount) throws ApiException {
        accountService.transfer(user.getId(), fromAccountID, toAccountID, amount);
        return ResponseEntity.status(200).body(user.getUsername()+"  transfer " + amount + "to" + toAccountID);
    }
}
