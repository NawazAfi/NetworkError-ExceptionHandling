package com.user;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/accounts")
//public class AccountController {
//
//    @Autowired
//    private AccountService accountService;
//
//    @PostMapping
//    public ResponseEntity<Object> createAccount(@RequestBody Account account) {
//        try {
//            Account createdAccount = accountService.createAccount(account);
//            return ResponseEntity.ok(createdAccount);
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(500).body(e.getMessage());
//        }
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Object> getAccount(@PathVariable Long id) {
//        try {
//            Account account = accountService.getAccount(id)
//                    .orElseThrow(() -> new RuntimeException("Account not found"));
//            return ResponseEntity.ok(account);
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(500).body(e.getMessage());
//        }
//    }
//
//    @PostMapping("/{id}/deposit")
//    public ResponseEntity<Object> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
//        try {
//            Double amount = request.get("amount");
//            return accountService.deposit(id, amount);
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(500).body(e.getMessage());
//        }
//    }
//
//    @PostMapping("/{id}/withdraw")
//    public ResponseEntity<Object> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
//        try {
//            Double amount = request.get("amount");
//            return accountService.withdraw(id, amount);
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(500).body(e.getMessage());
//        }
//    }
//}




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    private boolean isNetworkConnected() {
        try {
            InetAddress address =  InetAddress.getByName("www.google.com");
            return address.isReachable(5000);
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping
    public Object createAccount(@RequestBody Account account) {
    	
        if (!isNetworkConnected()) {
            return "Network error: No internet connection.";
        }
        return accountService.createAccount(account);
    }

    @GetMapping("/{id}")
    public Object getAccount(@PathVariable Long id) {
        if (!isNetworkConnected()) {
            return "Network error: No internet connection.";
        }
        return accountService.getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @PostMapping("/{id}/deposit")
    public Object deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        if (!isNetworkConnected()) {
            return "Network error: No internet connection.";
        }

        Double amount = request.get("amount");
        return accountService.deposit(id, amount);
    }

    @PostMapping("/{id}/withdraw")
    public Object withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        if (!isNetworkConnected()) {
            return "Network error: No internet connection.";
        }

        Double amount = request.get("amount");
        return accountService.withdraw(id, amount);
    }
}
