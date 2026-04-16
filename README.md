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
| **Aircraft**  | id, type, numberOfPassengers, airline_id |
| **Airline**   | id, name |
| **Gate**      | id, code, airport_id |
| **Flight**    | id, flightNumber, aircraft_id, airline_id, gate_id, origin_airport_id, destination_airport_id, departureTime, arrivalTime |

## Relationships

- City → many Airports
- City → many Passengers
- Airport → one City
- Airport → many Gates
- Airport → many Aircraft (many-to-many)
- Airport → many departing Flights (origin)
- Airport → many arriving Flights (destination)
- Gate → one Airport
- Gate → many Flights
- Airline → many Aircraft
- Airline → many Flights
- Aircraft → one Airline
- Aircraft → many Passengers (many-to-many)
- Aircraft → many Airports (many-to-many)
- Aircraft → many Flights
- Passenger → one City
- Passenger → many Aircraft (many-to-many)
- Passenger → many Flights (many-to-many)
- Flight → one Aircraft
- Flight → one Airline
- Flight → one Gate
- Flight → many Passengers (many-to-many)
- Flight → one origin Airport
- Flight → one destination Airport

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

## Running with Docker

To build and run the API with Docker:

```
docker-compose up --build
```

- The API will be available at http://localhost:8080
- MySQL will be available at localhost:3306 (root/HamburgerButtons5858)

To stop and remove containers:

```
docker-compose down
```

You can also run the app alone (without compose) after building the JAR:

```
docker build -t flight-api .
docker run -p 8080:8080 --env SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/flightdb?createDatabaseIfNotExist=true --env SPRING_DATASOURCE_USERNAME=root --env SPRING_DATASOURCE_PASSWORD=HamburgerButtons5858 flight-api
```

> **Note:** The default `application.properties` is set for local dev. Docker Compose overrides DB connection for containers.

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