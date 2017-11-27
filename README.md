# term-paper

Implementation of [term paper (just in Portuguese)](https://github.com/andreformento/tcc-engenharia/) in Bachelor of Science in Computer Engineering.

## How to

- Build all: `./build.sh`
- Run: `./start.sh`
- Performance test: `./test-report.sh`
- Clear all: `./clean.sh`

### Kubernetes

---- $ kubectl autoscale deployment nginx-deployment --min=10 --max=15 --cpu-percent=80

- Create or update configuration `kubectl apply -f deployment.yaml`
- Show application `kubectl get pods -l app=ticketreservation`
- Describe deployment `kubectl describe deployment realtimeticket-deployment`
- Kill all `kubectl delete deployment realtimeticket-deployment`

### Redis

#### Init and kill

```bash
docker run --rm --name my-redis -d redis:alpine
docker exec -it my-redis redis-cli
docker stop my-redis
```

#### Commands

- List all `keys *`

```bash
RPUSH event123-available u1 u2 u3 u4 u5 u6
RPOPLPUSH event123-available event123-reserved
LLEN event123-available
LRANGE event123-available 0 -1

DEL event123-available
SADD event123-available u1 u2 u3 u4 u5 u6
SPOP event123-available
```

#### References

- https://redis.io/commands
- https://www.concretepage.com/spring-4/spring-data-redis-example
- https://projects.spring.io/spring-data-redis/

## What to do

- Create an reservation event

```bash
curl -X POST 'http://localhost:8080/event-reservations' \
     -H 'Content-Type: application/json' \
     -w '\ntime: %{time_total}\n' \
     -d '{"eventId": "uuid456", "limit": 30}'
```

- Get event available tickets

```bash
curl -X GET 'http://localhost:8080/event-reservations/uuid456/available-tickets' \
     -w '\ntime: %{time_total}\n' \
     -H 'Content-Type: application/json'
```

- Booking an event

```bash
curl -X POST 'http://localhost:8080/events/uuid456/tickets' \
     -H 'Content-Type: application/json' \
     -w '\ntime: %{time_total}\n' \
     -d '{"idUser": "uuid123", "count": 3}'
```
