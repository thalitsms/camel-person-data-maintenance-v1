# Run The Bank Challenge- Person Data Maintenance Application

## Overview

The Run The Bank - Person Data Maintenance Application is a comprehensive banking application designed to manage personal data, accounts, and transactions efficiently. Built with Spring Boot, it offers a range of functionalities including CRUD operations for person data, account management, transaction handling, and integration with external notification services.

## Features

- **Personal Data Management:** Create, consult, update, and delete personal information of bank customers.
- **Account Management:** Open new accounts, manage account balances, and perform operations like deposits and account status updates.
- **Transaction Services:** Handle money transfers between accounts and including transaction reversals.

## Getting Started

### Prerequisites

- Java JDK 17 or higher
- Maven 3.6 or higher
- An IDE (like IntelliJ IDEA, Eclipse, etc.) or a text editor
- (Optional) Postman or any API testing tool

### Installation

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/camel-person-data-maintenance-v1.git
   cd camel-person-data-maintenance-v1
Build the Application:

```bash
mvn clean install
```
Run the Application:

```bash
mvn spring-boot:run
```
The application will start and run on http://localhost:8080.

### Usage
Person Management

Create Person:

**POST /persons**

Body:
```json
{
   "firstName":"ANA",
   "surName":"VIOLETA",
   "lastName":"GERLOFF",
   "documentNumber":"44422254588",
   "documentType":"F",
   "gender":"F",
   "dateOfBirth":"25-01-2000",
   "mothersName":"NENA MARIA",
   "fathersName":"NENO ROGERIO",
   "stateOfBirth":"SP",
   "cityOfBirth": "SAO PAULO",
   "countryOfBirth": "BRASIL",
   "address": "RUA AGOSTINHO",
   "email": "EMAIL@EMAIL.COM",
   "phone": "5511 99999-9999",
   "personalStatus": "E",
   "politicalExposedPersonIndicator": "N",
   "password": "123456"
}

```
Get Person by ID:

**GET /persons/{id}**

Update Person:

**PUT /persons/{id}**

Body:
```json
{
   "firstName":"ANA",
   "surName":"VIOLETA",
   "lastName":"GERLOFF",
   "documentNumber":"44422254588",
   "documentType":"F",
   "gender":"F",
   "dateOfBirth":"25-01-2000",
   "mothersName":"NENA MARIA",
   "fathersName":"NENO ROGERIO",
   "stateOfBirth":"SP",
   "cityOfBirth": "SAO PAULO",
   "countryOfBirth": "BRASIL",
   "address": "RUA AGOSTINHO",
   "email": "EMAIL@EMAIL.COM",
   "phone": "5511 99999-9999",
   "personalStatus": "E",
   "politicalExposedPersonIndicator": "N",
   "password": "123456"
}

```
Delete Person:
*DELETE /persons/{id}*

**Account Management**

Open Account:
POST /accounts/{documentNumber}

Body:
```json
{
   "firstName":"ANA",
   "surName":"VIOLETA",
   "lastName":"GERLOFF",
   "documentNumber":"44422254588",
   "documentType":"F",
   "gender":"F",
   "dateOfBirth":"25-01-2000",
   "mothersName":"NENA MARIA",
   "fathersName":"NENO ROGERIO",
   "stateOfBirth":"SP",
   "cityOfBirth": "SAO PAULO",
   "countryOfBirth": "BRASIL",
   "address": "RUA AGOSTINHO",
   "email": "EMAIL@EMAIL.COM",
   "phone": "5511 99999-9999",
   "personalStatus": "E",
   "politicalExposedPersonIndicator": "N",
   "password": "123456"
}

```

Get All Accounts by Person Document Number:

**GET /accounts/person/{documentNumber}**

Delete Account by Branch Code:

**DELETE /accounts/{branchCode}**

**Transaction Services**

Transfer Transaction:

**POST /transactions**

Body:
```json
{
   "fromAccountId": 2,
   "toAccountId": 1,
   "amount": 150.00
}
```
Reversal Transactions

**POST /transactions/reverse/{transactionId**}

### Contributing

Contributions, issues, and feature requests are welcome. Feel free to check issues page if you want to contribute.

*This README provides a basic overview. Detailed API documentation can be accessed at http://localhost:8080/swagger-ui/ after running the application.*