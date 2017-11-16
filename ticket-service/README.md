# Ticket Service

## Requirements

- Java 8
- Gradle 3
- Docker

## How to

- Create Docker image: `gradle buildDocker`

- Run: `docker run --rm -i -p 8080:8080 andreformento/realtimeticket-ticketservice` or -d

- Publish Docker image
```bash
docker login
docker push andreformento/realtimeticket-ticketservice
```

- Booking an event

```bash
curl -X POST 'http://localhost:8080/events/uuid456/tickets' \
     -H 'Content-Type: application/json' \
     -d '{"idUser": "uuid123"}'
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

- Run gatling local: `gradle gatlingRun`
