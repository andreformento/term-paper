# Ticket Service

## Requirements

- Java 8
- Gradle 3
- Docker

## How to

- Create `gradle buildDocker`

- Publish
```bash
docker login
docker push andreformento/realtimeticket-ticketservice
```

- (Requirement) Database `docker run --name mongodb --rm -d -p 27017:27017 mongo:3.4`

### Run with Docker

- Run

```bash
docker run \
       --link=mongodb \
       --rm -d \
       -p 8080:8080 \
       -e SPRING_DATA_MONGODB_URI='mongodb://mongodb/test' \
       --name realtimeticket-ticketservice \
       andreformento/realtimeticket-ticketservice
```

- Show all `docker ps -a`

- Kill all `docker kill mongodb realtimeticket-ticketservice`

### Run with Kubernetes

- Initilialize `minikube start`

- Run application
```bash
kubectl run \
        --image=andreformento/realtimeticket-ticketservice \
        realtimeticket-ticketservice-app \
        --port=8080 \
        --rm
        --env="SPRING_DATA_MONGODB_URI='mongodb://mongodb/test'"
```

- Expose application
```bash
kubectl expose deployment realtimeticket-ticketservice-app \
        --port=8080 \
        --name=realtimeticket-ticketservice-http
```

- Show all `kubectl get po -a`

- Kill all `kubectl delete deployment realtimeticket-ticketservice-app && docker kill mongodb`

- Stop `minikube stop`

### Use application

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

## References
- https://kubernetes.io/docs/user-guide/docker-cli-to-kubectl/#docker-run
