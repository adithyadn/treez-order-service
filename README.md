# treez-order-service
Treez Simple Order Service 

### Instruction

To startup the REST API, run the spring boot application using below command.
```shell script
$ cd treez-order-service/
$ ls
```

Once you see below directories:
```
.
├── HELP.md
├── README.md
├── SampleRequests.md
├── mvnw
├── mvnw.cmd
├── order-service.iml
├── pom.xml
├── src
└── target

```

Command to run spring-boot application:
```shell script
$ mvn spring-boot:run
```

### [Sample Requests](SampleRequests.md)

### Discussion:
#### Language: Java 8
#### Database: H2 Database - In-memory Relational Database. 
- Based on the requirements for the assessment, we notice that data is structured and transactional in nature. 
- RDBMS is more focused on consistency and ACID transactions, whereas NoSQL databases are focused on CAP theorem. NoSQL databases like AWS Dynamo DB has eventual consistency as compared RDBMS immidiate consistency. 
- For this usecase, ACID transactions seem to be critical. 

#### Framework: Spring Boot, Spring Data JPA. 
- The queries needed to serve the purpose of the assessment, we just needed basic CRUD queries to be performed. 
- Spring Data JPA provides CRUD capabilities to any domain object without the need of any boilerplate code.
- In general, Spring Data JPA minimizes the amount of source code needed to write custom queries.

#### Cancel orders:
- When we cancel orders, it would be ideal to still hold orders in the databases and not delete them. 
- There is no hard delete of orders implemented, Only soft deletes are implemented by marking the order as CANCELLED. 
- HTTP Delete method is utilized to perform this soft delete operation. 
- Once order is cancelled, the products are restocked. 

#### Insufficient Inventory:
- In an event of insufficient stock, the create order or update order API calls will raise exception and  HTTP 409 (Conflict) status code is returned to the client. 

#### No Resource Found:
- In scenarios where the product or order is not found, an exception is raised and HTTP 404 is returned to client. 

#### Possible enhancements:
- Convert current implementation into two micro-services (orders, inventory). This way we can isolate failures, independently scale as needed. 
- If we decide to change to micro-services implemented, we will need implement Circuit-breaker pattern.
- Swagger API, for automatic generation of API documentation. 
- Extensive Logging 
