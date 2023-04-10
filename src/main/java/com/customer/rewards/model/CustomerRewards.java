package com.customer.rewards.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Builder
public class CustomerRewards {

    private String customerId;

    private String month;

    @EqualsAndHashCode.Exclude
    private Integer rewardPoints;
}
