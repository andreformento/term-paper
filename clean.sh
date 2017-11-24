#!/usr/bin/env sh

docker-compose --file docker-compose-test.yml down
docker-compose down

cd ticket-reservation
./gradlew clean
cd ..
