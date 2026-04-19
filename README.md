
# Flight Management API

A full-stack project for Semester 4 Final Sprint. This Spring Boot REST API manages cities, airports, passengers, aircraft, airlines, and gates, and is designed to meet all assignment requirements:

---

## Assignment Requirements Checklist

- [x] **At least 4 entities with relationships** (City, Airport, Passenger, Aircraft, Airline, Gate, Flight)
- [x] **Full CRUD for all entities** (API and tested)
- [x] **Object-Oriented Spring Boot backend**
- [x] **MySQL database integration**
- [x] **Authentication & validation** (Spring Security, DTO/entity validation)
- [x] **Comprehensive backend unit tests** (controllers/services)
- [x] **CI/CD with GitHub Actions**
- [x] **Docker & Docker Compose support**
- [x] **Clean code, documentation, and CORS config**

---

## Project Status & Accomplishments

### ✅ Core Features Implemented
- **Full CRUD** for Aircraft, Airport, Passenger, Flight, Airline, Gate, City
- **Entity Relationships**: All major relationships (one-to-many, many-to-many) are modeled and working
- **Authentication**: All API endpoints are protected with secure username/password authentication (Spring Security)
- **Validation**: Robust validation for all DTOs and entities; clear error messages for invalid input
- **CORS**: Configured for safe frontend/backend integration
- **Seed Data**: Loads initial data at startup for easy testing

### ✅ Testing & Quality
- **Comprehensive Unit Tests**: All backend logic is covered by passing unit tests (Maven: `./mvnw clean test`)
- **CI/CD**: GitHub Actions workflow runs build and tests on every push/PR to `main` using a real MySQL service
- **Docker Support**: API and MySQL can be run together with Docker Compose; standalone Docker run supported

### ✅ Clean Code & Documentation
- **Object-Oriented Design**: Follows clean code and OOP best practices
- **Codebase Structure**: Clear separation of controller, service, repository, and model layers
- **README**: Updated with setup, usage, and endpoint documentation

### ⬜️ Next Steps
- Document frontend manual test scenarios (user stories)
- Final code/documentation polish
- Deploy backend, DB, and frontend to AWS
- Prepare and submit final documentation and demo video

---

## Tech Stack

- **Spring Boot** – REST API framework
- **MySQL** – Relational database
- **JPA/Hibernate** – ORM
- **Maven** – Build tool

## Features

- Full CRUD operations (GET, POST, PUT, DELETE)
- Arrivals/Departures endpoints for airports
- Seed data loaded at startup
- Complete entity relationships
- Validation and error handling
- Postman-testable endpoints

## Key Endpoints

### Flights
- `GET /flights` – List all flights
- `GET /flights/{id}` – Get flight by ID
- `POST /flights` – Create flight
- `PUT /flights/{id}` – Update flight
- `DELETE /flights/{id}` – Delete flight
- `GET /flights/arrivals/{airportId}` – List all flights arriving at airport
- `GET /flights/departures/{airportId}` – List all flights departing from airport

### Example: Get Arrivals for Airport

```
GET /flights/arrivals/3
```
Returns all flights where airport 3 is the destination.

### Validation
- All flight fields are required (flight number, aircraft, airline, gate, origin, destination, departure/arrival time).
- Invalid or missing data returns HTTP 400 with a validation message.

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
docker run -p 8080:8080 \
	--env SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/flightdb?createDatabaseIfNotExist=true \
	--env SPRING_DATASOURCE_USERNAME=root \
	--env SPRING_DATASOURCE_PASSWORD=<yourpassword> \
	flight-api
```


> The default `application.properties` is set for local dev. Docker Compose overrides DB connection for containers.

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