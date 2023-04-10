package com.customer.rewards.controller;

import com.customer.rewards.entity.CustomerTransactionEntity;
import com.customer.rewards.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<CustomerTransactionEntity>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }
}
