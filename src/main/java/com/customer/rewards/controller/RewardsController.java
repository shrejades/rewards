package com.customer.rewards.controller;

import com.customer.rewards.model.CustomerRewards;
import com.customer.rewards.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rewards")
public class RewardsController {
    @Autowired
    private RewardsService rewardsService;

    @GetMapping
    public ResponseEntity<List<CustomerRewards>> getAllCustomerRewards() {
        return ResponseEntity.ok(rewardsService.summarizeAllCustomerRewards());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<CustomerRewards>> getCustomerRewardsById(@PathVariable String customerId) {
        return ResponseEntity.ok(rewardsService.summarizeCustomerRewardsById(customerId));
    }
}

