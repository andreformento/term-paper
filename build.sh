#!/usr/bin/env sh

cd ticket-reservation
gradle clean
gradle buildDocker # -x test
cd ..
