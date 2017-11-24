#!/usr/bin/env sh

./clean.sh

cd ticket-reservation
./gradlew buildDocker # -x test
cd ..
