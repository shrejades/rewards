package com.customer.rewards.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerTransaction {

    private String customerId;

    private String transactionId;

    private Date purchaseDate;

    private Integer purchaseAmt;
}
