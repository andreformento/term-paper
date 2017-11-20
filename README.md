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

### Kubernetes

TODO


## What to do

- Booking an event

```bash
curl -X POST 'http://localhost:8080/events/uuid456/tickets' \
     -H 'Content-Type: application/json' \
     -d '{"idUser": "uuid123"}'
```
