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
- [x] **Manual test scenarios/user stories documented**
- [x] **Team collaboration, PR workflow, and project board**

---

## Project Status & Accomplishments

### ✅ Core Features Implemented
- **Full CRUD** for Aircraft, Airport, Passenger, Flight, Airline, Gate, City
- **Entity Relationships**: All major relationships (one-to-many, many-to-many) are modeled and working
- **Authentication**: All API endpoints are protected with secure username/password authentication (Spring Security). Signup is public; all other endpoints require login.
- **Validation**: Robust validation for all DTOs and entities; clear error messages for invalid input
- **CORS**: Configured for safe frontend/backend integration
- **Seed Data**: Loads initial data at startup for easy testing

### ✅ Testing & Quality
- **Comprehensive Unit & Integration Tests**: All backend logic is covered by passing unit and integration tests (Maven: `./mvnw clean test`).
- **Test Coverage**: All entities and relationships are tested for CRUD and delete-cascade logic. See `EntityDeleteIntegrationTests` and controller/service tests.
- **CI/CD**: GitHub Actions workflow runs build and tests on every push/PR to `main` using a real MySQL service
- **Docker Support**: API and MySQL can be run together with Docker Compose; standalone Docker run supported

### ✅ Clean Code & Documentation
- **Object-Oriented Design**: Follows clean code and OOP best practices
- **Codebase Structure**: Clear separation of controller, service, repository, and model layers
- **README**: Updated with setup, usage, endpoint documentation, and user stories

### ✅ Deployment
- **PR Workflow**: All changes via PRs, with reviews and trunk-based branching
- **Project Board**: https://trello.com/invite/b/69de58a1c2d6034d35ecdd8d/ATTI4d4af2b9f708c712bb04daaae6fd7343CF9AC032/sdat-devops-sprint used for planning and tracking
- **Frontend Repo**: [Frontend Repository]https://github.com/CactusTournament/Flight-management-frontend

---

## User Stories & Manual Test Scenarios

- As a user, I can sign up for an account with a username, password, email, phone number, and country.
- As a user, I can log in and access all API endpoints after authentication.
- As an admin, I can add, update, or delete flights, aircraft, airports, airlines, gates, and cities.
- As a user, I can view arrivals and departures for any airport.
- As a user, I can view, update, or delete my passenger profile.
- As an admin, I can assign aircraft to airports and flights to gates.
- As a user, I can see all relationships (e.g., which flights are at which gates, which aircraft are at which airports).
- As a user, I cannot access protected endpoints without logging in (401 Unauthorized).
- As a user, I receive clear error messages for invalid input or failed authentication.

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
| **Passenger** | id, firstName, lastName, phoneNumber, email, username, country, city_id (optional) |
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
- Passenger → one City (optional)
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

- All controller and service logic is covered by unit and integration tests (see `/src/test/java`).
- `EntityDeleteIntegrationTests` ensures cascade-safe deletes for all entities.
- Run all tests with:
  ```bash
  ./mvnw clean test
  ```
- Postman collection included for manual API testing.

---

Author: Brandon Maloney & SD14