# Credit Account Manager

A simple credit account manager app that supports basic operations like payment and withdrawal

## Description

This app has been built in Java and Spring boot, and includes some basic testing for useful logical parts

## Getting Started

### Dependencies

* This project uses an in-memory database for storage.
* This project includes dependencies defined in Maven pom.xml including frameworks and testing libraries

### Installing and app startup

1. Clone this project into your local filesystem

2. From the project root folder, run the following command to create the jar
```
./mvnw clean package
```
3. Copy the generated jar into the docker folder
```
cp target/account-manager-0.0.1-SNAPSHOT.jar src/main/docker
```
4. Start the docker container
```
cd src/main/docker
docker-compose up
```
The docker output should indicate the application has started. Also note that we are 
loading the operation types on our account including options for Withdrawal/Payment.

### Executing program
1. Create an account using a tool for API testing(like Postman)
   or, In a new terminal
```
curl -X POST \
  http://localhost:8080/accounts/ \
  -H 'content-type: application/json' \
  -d '{
	"documentNumber": "12345678900"
}
'
```
Verify that account table has been populated with this account from the curl output.
Note the balance is initialized to 0. We can also verify this balance with a GET call on the account

```
curl -X GET \
  http://localhost:8080/accounts/1 \
  -H 'content-type: application/json'
```

2. Let us use this account now to create a payment transaction
```
curl -X POST \
  http://localhost:8080/transactions/ \
  -H 'content-type: application/json' \
  -d '{
		"account_id": 1,
		"operation_type_id": 4,
		"amount": 50
}
'
```
Verify that transaction has been added by viewing the API call output, and 
the account has an updated balance of 50 based on this transaction

3. Let us now make a withdrawal on the same account
```
curl -X POST \
  http://localhost:8080/transactions/ \
  -H 'content-type: application/json' \
  -d '{
		"account_id": 1,
		"operation_type_id": 3,
		"amount": 60
}
'
```
the account has an updated balance of -10 based on this transaction

