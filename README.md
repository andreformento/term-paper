# term-paper

Implementation of [term paper (just in Portuguese)](https://github.com/andreformento/tcc-engenharia/) in Bachelor of Science in Computer Engineering.

## How to

- Build all: `./build.sh`

### Docker

- Run: `docker-compose up -d`
- Stop: `docker-compose down`

#### Test

- After your services is runnning, run this

```bash
docker-compose --file docker-compose-test.yml up
```

- And stop

```bash
docker-compose --file docker-compose-test.yml down
```

### Kubernetes

TODO

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
     -d '{"eventId": "uuid456", "limit": 30}'
```

- Booking an event

```bash
curl -X POST 'http://localhost:8080/events/uuid456/tickets' \
     -H 'Content-Type: application/json' \
     -d '{"idUser": "uuid123", "count": 3}'
```
