package com.customer.rewards.entity;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;

@Data
@Entity
public class CustomerEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String customerId;
	
	@Column
	private String customerName;
}
