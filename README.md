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

