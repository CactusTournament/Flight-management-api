# Flight Management API

A Spring Boot REST API for managing cities, airports, passengers, and aircraft. This API powers the Flight CLI application with full CRUD support.

## Tech Stack

- **Spring Boot** – REST API framework
- **MySQL** – Relational database
- **JPA/Hibernate** – ORM
- **Maven** – Build tool

## Features

 Full CRUD operations (GET, POST, PUT, DELETE)  
 Seed data loaded at startup  
 Complete entity relationships  
 Postman-testable endpoints  

## Data Model

| Entity        |                     Fields                      |
|---------------|-------------------------------------------------|
| **City**      | id, name, state, population |
| **Airport**   | id, name, code, city_id |
| **Passenger** | id, firstName, lastName, phoneNumber, city_id |
| **Aircraft**  | id, type, airlineName, numberOfPassengers |

## Relationships

- City → many Airports
- Passenger → one City
- Passenger ↔ many Aircraft (many-to-many)
- Aircraft ↔ many Airports (many-to-many)

## Getting Started

### 1. Database Setup
```sql
CREATE DATABASE flightdb;
```

### 2. Configure `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/flightdb
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
spring.sql.init.mode=always
```

### 3. Run the API
```bash
mvn spring-boot:run
```

API available at `http://localhost:8080`

## Project Structure

```
src/main/java/com/sprint/flightapi/
├── controller/
├── service/
├── repository/
├── model/
└── FlightApiApplication.java
```

## Testing

Postman collection included. Test endpoints:
- `GET /cities/{id}` – Retrieve city
- `GET /passengers/{id}/aircraft` – Passenger flights
- `POST /cities` – Create city
- `PUT /cities/{id}` – Update city
- `DELETE /cities/{id}` – Delete city

Author: Brandon Maloney & SD14