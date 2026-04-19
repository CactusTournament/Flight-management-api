-- Cities
INSERT INTO city (id, name, state, population) VALUES
(1, 'Toronto', 'ON', 3000000),
(2, 'Vancouver', 'BC', 675000),
(3, 'Montreal', 'QC', 1780000);

-- Airports
INSERT INTO airport (id, name, code, city_id) VALUES
(1, 'Toronto Pearson International', 'YYZ', 1),
(2, 'Billy Bishop Toronto City Airport', 'YTZ', 1),
(3, 'Vancouver International', 'YVR', 2),
(4, 'Montreal Trudeau International', 'YUL', 3);

-- Passengers
-- BCrypt hash for 'password': $2a$10$7EqJtq98hPqEX7fNZaFWoO5rG6WzQ4b6h0D4bFgh0QW8QW8QW8QW8
INSERT INTO passenger (id, first_name, last_name, phone_number, city_id, username, password, role) VALUES
(1, 'Brandon', 'Smith', '555-1111', 1, 'brandon', '$2a$12$C0rKdPHRWavn0swxCwblc.Qc2vZOLOL0Rycdb.fcWP1GrcJo2taIC', 'ADMIN'),
(2, 'Alice', 'Johnson', '555-2222', 2, 'alice', '$2a$12$C0rKdPHRWavn0swxCwblc.Qc2vZOLOL0Rycdb.fcWP1GrcJo2taIC', 'USER'),
(3, 'David', 'Lee', '555-3333', 3, 'david', '$2a$12$C0rKdPHRWavn0swxCwblc.Qc2vZOLOL0Rycdb.fcWP1GrcJo2taIC', 'USER');

-- Aircraft
INSERT INTO aircraft (id, type, airline_name, number_of_passengers) VALUES
(1, 'Boeing 737', 'Air Canada', 180),
(2, 'Airbus A320', 'WestJet', 160),
(3, 'Boeing 777', 'Air Canada', 396);

INSERT INTO aircraft_passenger (aircraft_id, passenger_id) VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 3);

-- Aircraft ↔ Airport (many-to-many)
INSERT INTO aircraft_airport (aircraft_id, airport_id) VALUES
(1, 1),
(1, 3),
(2, 1),
(2, 4),
(3, 3),
(3, 4);

-- Airlines
INSERT INTO airline (id, name) VALUES
(1, 'Air Canada'),
(2, 'WestJet');

-- Gates
INSERT INTO gate (id, code, airport_id) VALUES
(1, 'A1', 1),
(2, 'B2', 2),
(3, 'C3', 3);

INSERT INTO flight (id, flight_number, aircraft_id, airline_id, gate_id, origin_airport_id, destination_airport_id, departure_time, arrival_time) VALUES
(1, 'AC101', 1, 1, 1, 1, 3, '2026-04-14T08:00:00', '2026-04-14T11:00:00'),
(2, 'WS202', 2, 2, 2, 2, 4, '2026-04-15T09:00:00', '2026-04-15T12:00:00'),
(3, 'AC102', 1, 1, 1, 3, 1, '2026-04-16T13:00:00', '2026-04-16T16:00:00'),
(4, 'WS203', 2, 2, 2, 4, 2, '2026-04-17T14:00:00', '2026-04-17T17:00:00'),
(5, 'AC103', 1, 1, 1, 1, 4, '2026-04-18T10:00:00', '2026-04-18T13:00:00');

-- Flight ↔ Passenger (many-to-many)
INSERT INTO flight_passenger (flight_id, passenger_id) VALUES
(1, 1),
(1, 2),
(2, 2),
(2, 3);