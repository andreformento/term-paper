#!/usr/bin/env sh

startApplication="./start.sh"
finishApplication="./clean.sh"
requests_count=3000

# http://wiki.bash-hackers.org/howto/getopts_tutorial
# https://unix.stackexchange.com/questions/129391/passing-named-arguments-to-shell-scripts
while getopts "hr:n" opt; do
    case $opt in
        h)
            host="$OPTARG"
            ;;
        n)
            startApplication="echo 'no start application - remote'"
            finishApplication="docker-compose --file docker-compose-test.yml down"
            $finishApplication
            ;;
        r)
            requests_count="$OPTARG"
            echo "requests_count = $requests_count"
            ;;
        \?)
            echo "Invalid option: -$OPTARG" >&2
            Usage
            exit 1
            ;;
        :)
            echo "Option -$OPTARG requires an argument." >&2
            Usage
            exit 1
            ;;
        esac
    done

if [ -z "$host" ]; then
    echo "You can configure host:"
    echo "$0 -h http://myip:8080"
    host="http://localhost:8080"
    hostInsideContainer="http://ticketreservation:8080"
else
    hostInsideContainer=$host
fi

$startApplication

echo "param -r = $requests_count"

printf "Waiting for application is ready at $host"
until $(curl --output /dev/null --silent --head --fail $host/application); do
    printf '.'
    sleep 0.1
done

printf '\n'
curl -v -w '\n%{time_total}\n' -X POST "$host/event-reservations" -H 'Content-Type: application/json' -d '{"eventId": "uuid456", "limit": 100005}'
printf '\n'

export JAVA_OPTS_TEST="-Drequests_count=$requests_count -Dhostname_test=$hostInsideContainer"
docker-compose --file docker-compose-test.yml up

printf '\n available-tickets: '
curl -X GET "$host/event-reservations/uuid456/available-tickets" -H 'Content-Type: application/json'
printf '\n'

$finishApplication
