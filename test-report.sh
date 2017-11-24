#!/usr/bin/env sh

./clean.sh
#./build.sh

docker-compose up -d

printf 'Waiting for application is ready'
until $(curl --output /dev/null --silent --head --fail http://localhost:8080/application); do
    printf '.'
    sleep 0.1
done

printf '\n'
curl -v -w '\n%{time_total}\n' -X POST 'http://localhost:8080/event-reservations' -H 'Content-Type: application/json' -d '{"eventId": "uuid456", "limit": 90005}'
printf '\n'

docker-compose --file docker-compose-test.yml up

./clean.sh
