# Ticket booking system for multiplex cinema
Semi advanced demo RestAPI cinema system with reservations.

## RestAPI use case
Standard URI: `api/v1/multiplex`

| Info | Type | Param required | Endpoint | Description |
| ---- | - | - | -------------| -----------------|
| Movies | GET | Yes | /movies/?start=&XYZend=XYZ | List movies available in the given time interval |
| Screenings | GET | Yes | /screenings/{id}/seats | Information regarding screening room and available seats |
| Reservation | POST | Yes | /reservations | Create reservation |

## Getting started with build application
You can choose your way, run it with docker image or by yourself.

<details><summary>Docker Image</summary>
<p>

Go with simple docker command:

```docker
docker run --name ticket-app --publish 8080:8080 --network=ticket -e SPRING_DATASOURCE_USERNAME=patryk -e SPRING_DATASOURCE_URL=jdbc:postgresql://ticket-postgres:5432/multiplex -e SPRING_DATASOURCE_PASSWORD=password -d patrykpszeniczny/ticket-booking-app
```
Depending on your needs, I still leave the command to inicialize the postgres container
```docker
docker run --name ticket-postgres --publish 5432:5432 --network=ticket -e POSTGRES_PASSWORD=password -e POSTGRES_USER=patryk -e POSTGRES_DB=multiplex -d postgres
```

</p> </details>
<details><summary>Release jar</summary>
<p>

1. Download the latest release
2. Run application (remember about overwritting postgres db datasource)
Example:
```command
java -jar ticket.jar --spring.datasource.password=yourPass
```
</p>
</details>

## Test data
In general, the application at the start with the help of CommandLineRunner adds sample data, allowing full use of the application. However, if you would like to do it yourself, here is an example ticketapp-**testdata.sql** file
<details><summary>SQL queries Test data (postgresql)</summary>


```sql
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
```
</details>

## Shell Rest requests (entire app use case cycle)
The shell script can be found for download in the project too.

<details><summary>Shell file</summary>


```bash
#!/bin/bash

# Wyświetlenie filmów wraz z czasami ich wyświetlania (use case 1 & 2)
echo "Komentarz: wyświetlenie filmów wraz z czasami ich wyświetlania w przedziale czasowym (use case 1 & 2)"
curl --location 'http://localhost:8080/api/v1/multiplex/movies?start=2023-11-10T10%3A00%3A00&end=2023-11-10T17%3A00%3A00'
echo -e "\n"

# Przerwa między żądaniami
sleep 1

# Wyświetlenie dostępnych siedzeń i nazwy pokoju (use case 3 & 4)
echo "Komentarz: wyświetlenie dostępnych siedzeń i nazwa pokoju (use case 3 & 4)"
curl --location 'http://localhost:8080/api/v1/multiplex/screenings/1/seats'
echo -e "\n"

# Przerwa między żądaniami
sleep 1

# Stworzenie rezerwacji (use case 5 & 6)
echo "Komentarz: stworzenie rezerwacji (use case 5 & 6)"
curl --location 'http://localhost:8080/api/v1/multiplex/reservations' \
--header 'Content-Type: application/json' \
--data '{
  "name": "Jon",
  "surname": "pszeniczny-Gokk",
  "screeningId": 1,
  "tickets": [
    {
      "seatId": 1,
      "ticketType": "ADULT"
    },
    {
      "seatId": 2,
      "ticketType": "ADULT"
    }
  ]
}'
echo -e "\n"

# Przerwa między żądaniami
sleep 1

# Walidacja rezerwacji (proba rezerwacji juz zajetego miejsca)
echo "Walidacja rezerwacji 1: Proba rezerwacji miejsca zajetego"
curl --location 'http://localhost:8080/api/v1/multiplex/reservations' \
--header 'Content-Type: application/json' \
--data '{
  "name": "Patryk",
  "surname": "Pszeniczny-Gokk",
  "screeningId": 1,
  "tickets": [
    {
      "seatId": 1,
      "ticketType": "ADULT"
    }
  ]
}'
echo -e "\n"

# Przerwa między wątkami
sleep 1

# Walidacja rezerwacji (proba pozostawienia pustego niezarezerwowanego przez nikogo miejsca, miedzy rezerwacjami)
echo "Walidacja rezerwacji 2: Proba pozostawienia wolnego miejsca miedzy rezerwowanymi, ktore nie jest zarezerwowane"
curl --location 'http://localhost:8080/api/v1/multiplex/reservations' \
--header 'Content-Type: application/json' \
--data '{
  "name": "Patryk",
  "surname": "Pszeniczny-Gokk",
  "screeningId": 1,
  "tickets": [
    {
      "seatId": 3,
      "ticketType": "ADULT"
    },
    {
      "seatId": 5,
      "ticketType": "ADULT"
    }
  ]
}'
echo -e "\n"

# Przerwa między wątkami
sleep 1

# Walidacja rezerwacji (proba rezerwacji z imieniem oraz nazwiskiem zaczynajacych sie z malej litery)
echo "Walidacja rezerwacji 3: Proba rezerwacji z imieniem oraz nazwiskiem zaczynajacych sie z malej litery"
curl --location 'http://localhost:8080/api/v1/multiplex/reservations' \
--header 'Content-Type: application/json' \
--data '{
  "name": "patryk",
  "surname": "pszeniczny-Gokk",
  "screeningId": 1,
  "tickets": [
    {
      "seatId": 3,
      "ticketType": "ADULT"
    }
  ]
}'
echo -e "\n"
```
</details>

## UML Database diagram
![image](https://github.com/patrykpszeniczny/ticket-booking-app/assets/50798031/0bcd071c-9648-4b7b-88e1-7985a35281fa)

## Additional assumptions 
1. I have additionally made an option to make the seat reservation, when trying to reserve seat 1 and 3, check if seat 2 is reserved. If it is, the validation allows the reservation of seats 1 and 3, despite the gap between them. (I was inspired by the Helios cinema network - helios.pl)
2. The application is covered with unit and integration tests, I also tested it in 2E2 form with Postman. It remains to complete the tests of the last service.
3. Ticket prices are defined as constant values in TicketType ENUM, it could be done with a database, in order to introduce some business marketing strategies regarding prices.
4. I skipped the rest of the requests, such as adding movies, deleting, updates, seats or other things, in order to reduce the working time, I made the necessary queries from the Business scenario use case.



























