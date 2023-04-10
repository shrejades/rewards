package com.customer.rewards.service;

import com.customer.rewards.entity.CustomerTransactionEntity;
import com.customer.rewards.repository.CustomerTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private CustomerTransactionRepository customerTransactionRepository;

    public List<CustomerTransactionEntity> getAllTransactions() {
        return (List<CustomerTransactionEntity>)customerTransactionRepository.findAll();
    }
}
