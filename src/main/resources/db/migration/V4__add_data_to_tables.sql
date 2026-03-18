INSERT INTO countries(name, continent) VALUES ('North Macedonia', 'Europe');
INSERT INTO countries(name, continent) VALUES ('Serbia', 'Europe');

INSERT INTO hosts(created_at, updated_at, name, surname, country_id) VALUES (now(), now(), 'Mladen', 'Jovanovski', 1);
INSERT INTO hosts(created_at, updated_at, name, surname, country_id) VALUES (now(), now(), 'Ivan', 'Ivanovski', 2);

INSERT INTO accommodations(created_at, updated_at, name, category, state, host_id, num_rooms) VALUES (now(), now(), 'Lord of the Rings', 'MOTEL', 'GOOD',1,  10);
INSERT INTO accommodations(created_at, updated_at, name, category, state, host_id, num_rooms) VALUES (now(), now(), 'Game of thrones', 'ROOM', 'BAD',2,  1);