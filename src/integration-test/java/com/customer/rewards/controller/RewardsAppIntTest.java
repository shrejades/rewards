package com.customer.rewards.controller;

import com.customer.rewards.RewardsApplication;
import com.customer.rewards.entity.CustomerTransactionEntity;
import com.customer.rewards.model.CustomerRewards;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest(classes = RewardsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RewardsAppIntTest {

    @LocalServerPort
    int randomServerPort;

    @Test
    public void testGetCustomerRewards() {
        RestAssured.baseURI = "http://127.0.0.1:" + randomServerPort;
        CustomerRewards[] rewardsList = RestAssured.given().basePath("/rewards").contentType(ContentType.JSON).when().get().then().assertThat().statusCode(200).extract().as(CustomerRewards[].class);
        Assertions.assertThat(rewardsList).isNotEmpty()
                .hasSize(6)
                .extracting(CustomerRewards::getCustomerId, CustomerRewards::getMonth, CustomerRewards::getRewardPoints)
                .contains(Tuple.tuple("741b9c16-3972-4e6a-b146-fb1e3c80d342", "Mar", 160),
                        Tuple.tuple("741b9c16-3972-4e6a-b146-fb1e3c80d342", "Apr", 780),
                        Tuple.tuple("741b9c16-3972-4e6a-b146-fb1e3c80d342", "May", 270),
                        Tuple.tuple("841b9c16-3972-4e6a-b146-fb1e3c80d342", "Jun", 160),
                        Tuple.tuple("841b9c16-3972-4e6a-b146-fb1e3c80d342", "Jul", 780),
                        Tuple.tuple("841b9c16-3972-4e6a-b146-fb1e3c80d342", "Aug", 270));
    }

    @Test
    public void testGetCustomerRewardsById() {
        RestAssured.baseURI = "http://127.0.0.1:" + randomServerPort;
        String customerId = "741b9c16-3972-4e6a-b146-fb1e3c80d342";
        CustomerRewards[] rewardsList = RestAssured.given().basePath("/rewards/"+customerId).contentType(ContentType.JSON).when().get().then().assertThat().statusCode(200).extract().as(CustomerRewards[].class);
        Assertions.assertThat(rewardsList).isNotEmpty()
                .hasSize(3)
                .extracting(CustomerRewards::getCustomerId, CustomerRewards::getMonth, CustomerRewards::getRewardPoints)
                .contains(Tuple.tuple("741b9c16-3972-4e6a-b146-fb1e3c80d342", "Mar", 160),
                        Tuple.tuple("741b9c16-3972-4e6a-b146-fb1e3c80d342", "Apr", 780),
                        Tuple.tuple("741b9c16-3972-4e6a-b146-fb1e3c80d342", "May", 270));
    }

    @Test
    public void testGetAllTransactions() {
        RestAssured.baseURI = "http://127.0.0.1:" + randomServerPort;
        CustomerTransactionEntity[] customerTransactionEntities = RestAssured.given().basePath("/transactions").contentType(ContentType.JSON).when().get().then().assertThat().statusCode(200).extract().as(CustomerTransactionEntity[].class);
        Assertions.assertThat(customerTransactionEntities).isNotEmpty()
                .hasSize(12);
    }
}
