-- Courts
INSERT INTO PADELCOURTS (court_name, max_players) VALUES ('Court 1', 4);
INSERT INTO PADELCOURTS (court_name, max_players) VALUES ('Court 2', 4);
INSERT INTO PADELCOURTS (court_name, max_players) VALUES ('Court 3', 2);

-- Bookings
INSERT INTO PADELBOOKINGS (customer, player_amount, court_id, booking_date, total_price_sek, total_price_euro, cancelled)
VALUES ('erik.eriksson@mail.se', 4, 1, '2025-10-13 18:00', 400, 35, FALSE);

INSERT INTO PADELBOOKINGS (customer, player_amount, court_id, booking_date, total_price_sek, total_price_euro, cancelled)
VALUES ('anna.andersson@mail.se', 2, 2, '2025-10-16 19:30', 200, 18, FALSE);

INSERT INTO PADELBOOKINGS (customer, player_amount, court_id, booking_date, total_price_sek, total_price_euro, cancelled)
VALUES ('anna.andersson@mail.se', 3, 1, '2025-10-17 17:00', 300, 26, TRUE);