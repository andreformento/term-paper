# Ticket Service

## Requirements

- Java 8
- Gradle 3
- Docker

## How to

- Create Docker image: `gradle buildDocker`

- Run: `docker run --rm -d -p 8080:8080 andreformento/realtimeticket-ticketservice`

- Publish Docker image
```bash
docker login
docker push andreformento/realtimeticket-ticketservice
```

- Performance test
```bash
BASE_PATH=$(pwd)'/src/test/gatling'
docker run -it --rm \
           -v $BASE_PATH/conf:/opt/gatling/conf \
           -v $BASE_PATH/user-files:/opt/gatling/user-files \
           -v $BASE_PATH/results:/opt/gatling/results \
           denvazh/gatling
```
