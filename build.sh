#!/usr/bin/env sh

cd ticket-service
gradle clean
gradle buildDocker
cd ..
