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
  "surname": "Pszeniczny-Gokk",
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