#!/usr/bin/env sh

docker-compose down
docker-compose up -d

printf 'Waiting for application is ready'
until $(curl --output /dev/null --silent --head --fail http://localhost:8080/health); do
    printf '.'
    sleep 0.1
done

curl -X POST 'http://localhost:8080/event-reservations' -H 'Content-Type: application/json' -d '{"eventId": "uuid456", "limit": 90005}'
docker-compose --file docker-compose-test.yml up
docker-compose down
