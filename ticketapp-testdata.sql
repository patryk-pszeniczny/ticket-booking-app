INSERT INTO movies (id, title, description, genre, rating, duration_time) VALUES
(1, 'Avengers: Infinity War', 'The Avengers must stop Thanos...', 'Action', 8.5, 160),
(2, 'The Godfather', 'The aging patriarch of an organized crime dynasty...', 'Drama', 9.2, 125),
(3, 'Forrest Gump', 'The presidencies of Kennedy and Johnson...', 'Drama', 8.8, 110);

INSERT INTO screening_rooms (id, name) VALUES
(1, 'Room A'),
(2, 'Room B'),
(3, 'Room C');

INSERT INTO screenings (id, movie_id, screening_room_id, screening_start_time) VALUES (1, 1, 1, '2023-11-10 15:00:00');
INSERT INTO screenings (id, movie_id, screening_room_id, screening_start_time) VALUES (2, 2, 2, '2023-11-10 20:00:00');
INSERT INTO screenings (id, movie_id, screening_room_id, screening_start_time) VALUES (3, 3, 1, '2023-11-10 13:00:00');
INSERT INTO screenings (id, movie_id, screening_room_id, screening_start_time) VALUES (3, 4, 3, '2023-11-10 14:00:00');

INSERT INTO seats(id, number, screening_room_id, row) VALUES (1, 1, 1, 'I');
INSERT INTO seats(id, number, screening_room_id, row) VALUES (2, 2, 1, 'I');
INSERT INTO seats(id, number, screening_room_id, row) VALUES (3, 3, 1, 'I');
INSERT INTO seats(id, number, screening_room_id, row) VALUES (4, 4, 1, 'I');
INSERT INTO seats(id, number, screening_room_id, row) VALUES (5, 5, 1, 'I');