 package com.user;



// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;
//
// import java.util.Optional;
//
// @Service
// public class AccountService {
//
//     @Autowired
//     private AccountRepository accountRepository;
//
//     @Value("${withdraw.timeout.seconds}")
//     private int withdrawTimeoutSeconds;
//
//     public Account createAccount(Account account) {
//         return accountRepository.save(account);
//     }
//
//     public Optional<Account> getAccount(Long id) {
//         return accountRepository.findById(id);
//     }
//
//     public ResponseEntity<Object> deposit(Long id, double amount) {
//         Account account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
//         account.setBalance(account.getBalance() + amount);
//         return ResponseEntity.ok(accountRepository.save(account));
//     }
//
//     public ResponseEntity<Object> withdraw(Long id, double amount) {
//         Account account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
//
//         long startTime = System.currentTimeMillis();
//
//         try {
//             simulateNetworkDelay(startTime);
//
//             if (account.getBalance() < amount) {
//                 throw new RuntimeException("Insufficient funds");
//             }
//
//             account.setBalance(account.getBalance() - amount);
//
//             return ResponseEntity.ok(accountRepository.save(account));
//         } catch (InterruptedException e) {
//             Thread.currentThread().interrupt();
//             return ResponseEntity.status(500).body("Network timeout");
//         }
//     }
//
//     private void simulateNetworkDelay(long startTime) throws InterruptedException {
//         long elapsedTime = System.currentTimeMillis() - startTime;
//
//         if (elapsedTime < withdrawTimeoutSeconds * 1000) {
//             // Simulate delay only if less than withdrawTimeoutSeconds has passed
//             Thread.sleep(withdrawTimeoutSeconds * 1000 - elapsedTime);
//         }
//     }
// }

 
 
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    public Account deposit(Long id, double amount) {
        Account account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);
    }

    public Account withdraw(Long id, double amount) {
        Account account = getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }
        account.setBalance(account.getBalance() - amount);
        return accountRepository.save(account);
    }
}

