# term-paper

Implementation of [term paper (just in Portuguese)](https://github.com/andreformento/tcc-engenharia/) in Bachelor of Science in Computer Engineering.

## How to

- Build all: `./build.sh`
- Run: `docker-compose up -d`
- Stop: `docker-compose down`

## What to do

- Booking an event

```bash
curl -X POST 'http://localhost:8080/events/uuid456/tickets' \
     -H 'Content-Type: application/json' \
     -d '{"idUser": "uuid123"}'
```
