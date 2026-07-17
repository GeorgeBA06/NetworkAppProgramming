# AkdevKafkaOrderApp
Sample event-driven microservice built with Spring Boot and Apache Kafka, featuring REST API, asynchronous message processing, and PostgreSQL persistence.


## Description

Order Processing Service is an event-driven Spring Boot application that demonstrates asynchronous order processing using Apache Kafka.
The application receives orders through a REST API, publishes order events to a Kafka topic, processes them asynchronously in a consumer service, and persists the processing results in PostgreSQL.

#Getting Started
Requirements:
Java 21+
Maven 3.9+
Docker
Docker Compose
PostgreSQL (or Docker container)

##Clone Repository:

git clone https://github.com/<your-username>/order-processing-service.git

cd order-processing-service

Build the Project:

mvn clean install

##Start Infrastructure:

docker compose up -d

##This starts:

Apache Kafka
Zookeeper
PostgreSQL

##Run the Application:

mvn spring-boot:run

Configuration

##The main configuration is located in:

src/main/resources/application.yml

##Important settings include:

Kafka bootstrap server
Kafka topic names
PostgreSQL connection
Server ports
Consumer group ID


##API:

#Create Order
POST /orders

#Request Body

{
   "userId": "user-123",
    "items": [
        {
            "sku": "IPHONE-15-PRO",
            "quantity": 1,
            "price": 999.99
        },
        {
            "sku": "MACBOOK-PRO-16",
            "quantity": 2,
            "price": 2499.50
        }
    ]
}

Project Structure:
src
├── config
├── controller
├── dto
├── entity
├── kafka
│   ├── consumer
│   ├── event
│   ├── producer
├── repository
├── service

