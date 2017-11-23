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

#### References

- https://redis.io/commands
- https://www.concretepage.com/spring-4/spring-data-redis-example
- https://projects.spring.io/spring-data-redis/

## What to do

- Booking an event

```bash
curl -X POST 'http://localhost:8080/events/uuid456/tickets' \
     -H 'Content-Type: application/json' \
     -d '{"idUser": "uuid123"}'
```
