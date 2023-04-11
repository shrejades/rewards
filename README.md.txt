### How to start the application?

Project build using - 'mvn clean package'
Project execution using - 'java -jar ./target/rewards-1.0.0-SNAPSHOT.jar'


### API Specification
APIs for fetching rewards for all transaction :
```
curl -X 'GET' \
  'http://localhost:8080/rewards' \
  -H 'accept: */*'

Response:
[
{"customerId":"741b9c16-3972-4e6a-b146-fb1e3c80d342","month":"Mar","rewardPoints":160},
{"customerId":"741b9c16-3972-4e6a-b146-fb1e3c80d342","month":"Apr","rewardPoints":780},
{"customerId":"741b9c16-3972-4e6a-b146-fb1e3c80d342","month":"May","rewardPoints":270},
{"customerId":"841b9c16-3972-4e6a-b146-fb1e3c80d342","month":"Jun","rewardPoints":160},
{"customerId":"841b9c16-3972-4e6a-b146-fb1e3c80d342","month":"Jul","rewardPoints":780},
{"customerId":"841b9c16-3972-4e6a-b146-fb1e3c80d342","month":"Aug","rewardPoints":270}
]

```

APIs for fetching the customer specific rewards :
```
curl -X 'GET' \
  'http://localhost:8080/rewards/741b9c16-3972-4e6a-b146-fb1e3c80d342' \
  -H 'accept: */*'

Response:
[
{"customerId":"741b9c16-3972-4e6a-b146-fb1e3c80d342","month":"Mar","rewardPoints":160},
{"customerId":"741b9c16-3972-4e6a-b146-fb1e3c80d342","month":"Apr","rewardPoints":780},
{"customerId":"741b9c16-3972-4e6a-b146-fb1e3c80d342","month":"May","rewardPoints":270}
]


```

Get All Transactions

```
curl -X 'GET' \
  'http://localhost:8080/transactions' \
  -H 'accept: */*'

Response:
[
{"transactionId":"1be9aac6-76e5-4624-ab77-493d18b0742a",
"customerEntity":{"customerId":"741b9c16-3972-4e6a-b146-fb1e3c80d342","customerName":"Customer1"},
"purchaseDate":"2022-03-16 18:30:00","purchaseAmount":120},
{"transactionId":"1be9aac6-76e5-4624-ab77-493d18b0742b",
"customerEntity":{"customerId":"741b9c16-3972-4e6a-b146-fb1e3c80d342","customerName":"Customer1"},
"purchaseDate":"2022-03-17 18:30:00","purchaseAmount":110},
{"transactionId":"1be9aac6-76e5-4624-ab77-493d18b0742c",
"customerEntity":{"customerId":"741b9c16-3972-4e6a-b146-fb1e3c80d342","customerName":"Customer1"},
"purchaseDate":"2022-04-18 18:30:00","purchaseAmount":220},
{"transactionId":"1be9aac6-76e5-4624-ab77-493d18b0742d",
"customerEntity":{"customerId":"741b9c16-3972-4e6a-b146-fb1e3c80d342","customerName":"Customer1"},
"purchaseDate":"2022-04-16 18:30:00","purchaseAmount":320},
{"transactionId":"1be9aac6-76e5-4624-ab77-493d18b0742e",
"customerEntity":{"customerId":"741b9c16-3972-4e6a-b146-fb1e3c80d342","customerName":"Customer1"},
"purchaseDate":"2022-05-17 18:30:00","purchaseAmount":210},
{"transactionId":"1be9aac6-76e5-4624-ab77-493d18b0742f",
"customerEntity":{"customerId":"741b9c16-3972-4e6a-b146-fb1e3c80d342","customerName":"Customer1"},
"purchaseDate":"2022-05-18 18:30:00","purchaseAmount":20},
{"transactionId":"2be9aac6-76e5-4624-ab77-493d18b0742a",
"customerEntity":{"customerId":"841b9c16-3972-4e6a-b146-fb1e3c80d342","customerName":"Customer2"},
"purchaseDate":"2022-06-16 18:30:00","purchaseAmount":120},
{"transactionId":"2be9aac6-76e5-4624-ab77-493d18b0742b",
"customerEntity":{"customerId":"841b9c16-3972-4e6a-b146-fb1e3c80d342","customerName":"Customer2"},
"purchaseDate":"2022-06-17 18:30:00","purchaseAmount":110},
{"transactionId":"2be9aac6-76e5-4624-ab77-493d18b0742c",
"customerEntity":{"customerId":"841b9c16-3972-4e6a-b146-fb1e3c80d342","customerName":"Customer2"},
"purchaseDate":"2022-07-18 18:30:00","purchaseAmount":220},
{"transactionId":"2be9aac6-76e5-4624-ab77-493d18b0742d",
"customerEntity":{"customerId":"841b9c16-3972-4e6a-b146-fb1e3c80d342","customerName":"Customer2"},
"purchaseDate":"2022-07-16 18:30:00","purchaseAmount":320},
{"transactionId":"2be9aac6-76e5-4624-ab77-493d18b0742e",
"customerEntity":{"customerId":"841b9c16-3972-4e6a-b146-fb1e3c80d342","customerName":"Customer2"},
"purchaseDate":"2022-08-17 18:30:00","purchaseAmount":210},
{"transactionId":"2be9aac6-76e5-4624-ab77-493d18b0742f",
"customerEntity":{"customerId":"841b9c16-3972-4e6a-b146-fb1e3c80d342","customerName":"Customer2"},
"purchaseDate":"2022-08-18 18:30:00","purchaseAmount":20}
]

```
### Note

Unit and Integration tests execution via - 'mvn clean test'
Note: Integration tests are executed with 2 customers transactions spanned over 3 months in data.sql file.
This file content gets loaded into memory before integration-test start.
Currently integration tests are runs via in-memory database.
