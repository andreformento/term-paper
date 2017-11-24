#!/usr/bin/env sh

docker-compose down
docker-compose up -d
sleep 4
curl -X POST 'http://localhost:8080/event-reservations' -H 'Content-Type: application/json' -d '{"eventId": "uuid456", "limit": 90005}'
docker-compose --file docker-compose-test.yml up
docker-compose down
