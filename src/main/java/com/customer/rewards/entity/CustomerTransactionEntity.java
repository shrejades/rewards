package com.customer.rewards.entity;

import java.sql.Timestamp;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class CustomerTransactionEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String transactionId;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	CustomerEntity customerEntity;
	
	@Column(name="PURCHASE_DATE")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	public Timestamp purchaseDate;

	@Column(name="PURCHASE_AMT")
	public Integer purchaseAmount;
}
