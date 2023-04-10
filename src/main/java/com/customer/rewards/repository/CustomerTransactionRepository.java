package com.customer.rewards.repository;

import com.customer.rewards.entity.CustomerTransactionEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTransactionRepository extends CrudRepository<CustomerTransactionEntity, String>, JpaSpecificationExecutor<CustomerTransactionEntity> {
}
