#!/usr/bin/env sh

cd ticket-reservation
./gradlew clean buildDocker # -x test
cd ..
