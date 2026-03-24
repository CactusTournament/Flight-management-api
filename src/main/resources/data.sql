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
INSERT INTO passenger (id, first_name, last_name, phone_number, city_id) VALUES
(1, 'Brandon', 'Smith', '555-1111', 1),
(2, 'Alice', 'Johnson', '555-2222', 2),
(3, 'David', 'Lee', '555-3333', 3);

-- Aircraft
INSERT INTO aircraft (id, type, airline_name, number_of_passengers) VALUES
(1, 'Boeing 737', 'Air Canada', 180),
(2, 'Airbus A320', 'WestJet', 160),
(3, 'Boeing 777', 'Air Canada', 396);

-- Passenger ↔ Aircraft (many-to-many)
INSERT INTO passenger_aircraft (passenger_id, aircraft_id) VALUES
(1, 1),
(1, 2),
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