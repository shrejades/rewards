package com.customer.rewards.repository;

import com.customer.rewards.model.CustomerTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomQueriesRepository {

    @Autowired
    private NamedParameterJdbcOperations jdbcOperations;

    public List<CustomerTransaction> findAllCustomerTransactions() {
        Map<String, Object> params = new HashMap<>();
        String sql = "select cust.customer_id, cust_tran.transaction_id, cust_tran.purchase_date, cust_tran.purchase_amt from customer_entity cust inner join customer_transaction_entity cust_tran on cust.customer_id=cust_tran.customer_id";
        return jdbcOperations.query(sql, params, BeanPropertyRowMapper.newInstance(CustomerTransaction.class));
    }

    public List<CustomerTransaction> findCustomerTransactions(String customerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", customerId);
        String sql = "select cust.customer_id, cust_tran.transaction_id, cust_tran.purchase_date, cust_tran.purchase_amt from customer_entity cust inner join customer_transaction_entity cust_tran on cust.customer_id=cust_tran.customer_id and cust.customer_id=:id";
        return jdbcOperations.query(sql, params, BeanPropertyRowMapper.newInstance(CustomerTransaction.class));
    }
}
