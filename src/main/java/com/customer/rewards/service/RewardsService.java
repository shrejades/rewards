package com.customer.rewards.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.customer.rewards.model.CustomerRewards;
import com.customer.rewards.model.CustomerTransaction;
import com.customer.rewards.repository.CustomQueriesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RewardsService {

    CustomQueriesRepository repository;

    private static int FIFTY = 50;

    private static int HUNDRED = 100;

    public int calculateRewards(int purchaseAmount) {
        int points;
        if(purchaseAmount <= FIFTY) {
            points = 0;
        } else if(purchaseAmount <= HUNDRED) {
            points = purchaseAmount - FIFTY;
        } else {
            points = FIFTY + (purchaseAmount - HUNDRED) * 2;
        }
        return points;
    }

    private List<CustomerRewards> generateCustomerRewards(List<CustomerTransaction> customerTransactions) {
        List<CustomerRewards> customerRewards = new ArrayList<>();
        customerTransactions.stream().forEach(transaction -> {
            CustomerRewards customerReward = customerRewards
                    .stream()
                    .filter(custReward -> custReward.getCustomerId().equals(transaction.getCustomerId())
                            &&
                            custReward.getMonth().equals(new SimpleDateFormat("MMM").format(transaction.getPurchaseDate())))
                    .findFirst()
                    .orElse(CustomerRewards.builder()
                            .customerId(transaction.getCustomerId())
                            .month(new SimpleDateFormat("MMM").format(transaction.getPurchaseDate()))
                            .rewardPoints(0)
                            .build());
            if(!customerRewards.contains(customerReward)) {
                customerRewards.add(customerReward);
            }
            customerReward.setRewardPoints(customerReward.getRewardPoints()+calculateRewards(transaction.getPurchaseAmt()));
        });
        return customerRewards;
    }
    public List<CustomerRewards> summarizeAllCustomerRewards() {
        List<CustomerTransaction> customerTransactions = repository.findAllCustomerTransactions();
        return generateCustomerRewards(customerTransactions);
    }

    public List<CustomerRewards> summarizeCustomerRewardsById(String customerId) {
        List<CustomerTransaction> customerTransactions = repository.findCustomerTransactions(customerId);
        return generateCustomerRewards(customerTransactions);
    }
}
