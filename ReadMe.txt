This project contains

1. All Customer rewards endpoint at http://localhost:8080/rewards
2. Get One Particular Customer Rewards by customerId at http://localhost:8080/rewards/{custid}.
3. Get All Transactions at http://localhost:8080/transactions

Project build using - 'mvn clean package'
Project execution using - 'java -jar ./target/rewards-1.0.0-SNAPSHOT.jar'

Unit and Integration tests execution via - 'mvn clean test'
Note: Integration tests are executed with 2 customers transactions spanned over 3 months in data.sql file.
This file content gets loaded into memory before integration-test start.
Currently integration tests are runs via in-memory database.