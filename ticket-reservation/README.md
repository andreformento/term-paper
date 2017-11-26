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
docker push andreformento/realtimeticket-ticketreservation
```

- (Requirement) Database `docker run --name redisdb --rm -d -p 6379:6379 redis:alpine`

### Run with Docker

- Run

```bash
docker run \
       --link=redisdb \
       --rm -d \
       -p 8080:8080 \
       -e SPRING_REDIS_HOSTNAME='redisdb' \
       -e SPRING_REDIS_PORT='6379' \
       --name realtimeticket-ticketreservation \
       andreformento/realtimeticket-ticketreservation
```

- Show all `docker ps -a`

- Kill all `docker kill redisdb realtimeticket-ticketreservation`

### Run with Kubernetes

- Initilialize `minikube start`

- Run application
```bash
kubectl run \
        --image=andreformento/realtimeticket-ticketreservation \
        realtimeticket-ticketreservation-app \
        --port=8080 \
        --env="SPRING_REDIS_HOSTNAME='redisdb'" \
        --env="SPRING_REDIS_PORT='6379'"
```

- Expose application
```bash
kubectl expose deployment realtimeticket-ticketreservation-app \
        --port=8080 \
        --name=realtimeticket-ticketreservation-http
```

- Show all `kubectl get po -a`

- Kill all `kubectl delete deployment realtimeticket-ticketreservation-app && docker kill redisdb`

- Stop `minikube stop`

## References
- https://kubernetes.io/docs/user-guide/docker-cli-to-kubectl/#docker-run
