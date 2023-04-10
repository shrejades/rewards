package com.customer.rewards.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.customer.rewards.model.CustomerTransaction;
import com.customer.rewards.repository.CustomQueriesRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.customer.rewards.model.CustomerRewards;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class RewardsServiceTest {

    @Test
    public void calculateRewards_shouldReturnZero_whenPurchaseAmountIsZero() {
        int purchaseAmount = 0;
        RewardsService rewardsService = new RewardsService(null);
        int result = rewardsService.calculateRewards(purchaseAmount);
        Assertions.assertThat(result).isEqualTo(0);
    }

    @Test
    public void calculateRewards_shouldReturnFifty_whenPurchaseAmountIsHundred() {
        int purchaseAmount = 100;
        RewardsService rewardsService = new RewardsService(null);
        int result = rewardsService.calculateRewards(purchaseAmount);
        Assertions.assertThat(result).isEqualTo(50);
    }

    @Test
    public void calculateRewards_shouldReturnNinety_whenPurchaseAmountIsOneHundredTwenty() {
        int purchaseAmount = 120;
        RewardsService rewardsService = new RewardsService(null);
        int result = rewardsService.calculateRewards(purchaseAmount);
        Assertions.assertThat(result).isEqualTo(90);
    }

    @Test
    public void calculateRewards_shouldReturnTwoHundredFifty_whenPurchaseAmountIsTwoHundred() {
        int purchaseAmount = 200;
        RewardsService rewardsService = new RewardsService(null);
        int result = rewardsService.calculateRewards(purchaseAmount);
        Assertions.assertThat(result).isEqualTo(250);
    }

    @Test
    public void summarizeRewards_shouldReturnRewards_whenCustomerTransactionsGiven() throws ParseException {
        List<CustomerTransaction> customerTransactions = new ArrayList<> ();
        customerTransactions.add(new CustomerTransaction("1","1", new SimpleDateFormat("dd/MM/yyyy").parse("10/03/2023"), 100));
        customerTransactions.add(new CustomerTransaction("1","2", new SimpleDateFormat("dd/MM/yyyy").parse("11/03/2023"), 120));
        customerTransactions.add(new CustomerTransaction("1","3", new SimpleDateFormat("dd/MM/yyyy").parse("10/04/2023"), 100));
        customerTransactions.add(new CustomerTransaction("1","4", new SimpleDateFormat("dd/MM/yyyy").parse("11/04/2023"), 120));

        customerTransactions.add(new CustomerTransaction("2","1", new SimpleDateFormat("dd/MM/yyyy").parse("10/03/2023"), 100));
        customerTransactions.add(new CustomerTransaction("2","2", new SimpleDateFormat("dd/MM/yyyy").parse("11/03/2023"), 120));
        customerTransactions.add(new CustomerTransaction("2","3", new SimpleDateFormat("dd/MM/yyyy").parse("10/04/2023"), 100));
        customerTransactions.add(new CustomerTransaction("2","4", new SimpleDateFormat("dd/MM/yyyy").parse("11/04/2023"), 110));
        CustomQueriesRepository repository = mock(CustomQueriesRepository.class);
        Mockito.when(repository.findAllCustomerTransactions()).thenReturn(customerTransactions);
        RewardsService rewardsService = new RewardsService(repository);
        List<CustomerRewards> customerRewards = rewardsService.summarizeAllCustomerRewards();

        Assertions.assertThat(customerRewards).isNotNull().hasSize(4)
                .extracting(CustomerRewards::getCustomerId)
                .contains("1", "1", "2", "2");

        Assertions.assertThat(customerRewards)
                .extracting(CustomerRewards::getMonth)
                .contains("Mar", "Apr", "Mar", "Apr");

        Assertions.assertThat(customerRewards)
                .extracting(CustomerRewards::getRewardPoints)
                .contains(140, 140, 140, 120);

    }

    @Test
    public void summarizeRewards_shouldReturnRewardsForSingleCustomer_whenCustomerTransactionsGiven() throws ParseException {
        List<CustomerTransaction> customerTransactions = new ArrayList<> ();
        customerTransactions.add(new CustomerTransaction("2","1", new SimpleDateFormat("dd/MM/yyyy").parse("10/03/2023"), 100));
        customerTransactions.add(new CustomerTransaction("2","2", new SimpleDateFormat("dd/MM/yyyy").parse("11/03/2023"), 120));
        customerTransactions.add(new CustomerTransaction("2","3", new SimpleDateFormat("dd/MM/yyyy").parse("10/04/2023"), 100));
        customerTransactions.add(new CustomerTransaction("2","4", new SimpleDateFormat("dd/MM/yyyy").parse("11/04/2023"), 110));
        CustomQueriesRepository repository = mock(CustomQueriesRepository.class);
        Mockito.when(repository.findCustomerTransactions(Mockito.anyString())).thenReturn(customerTransactions);
        RewardsService rewardsService = new RewardsService(repository);
        List<CustomerRewards> customerRewards = rewardsService.summarizeCustomerRewardsById("2");

        Assertions.assertThat(customerRewards).isNotNull().hasSize(2)
                .extracting(CustomerRewards::getCustomerId)
                .contains("2", "2");

        Assertions.assertThat(customerRewards)
                .extracting(CustomerRewards::getMonth)
                .contains("Mar", "Apr");

        Assertions.assertThat(customerRewards)
                .extracting(CustomerRewards::getRewardPoints)
                .contains(140, 120);

    }
}
