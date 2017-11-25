#!/usr/bin/env sh

startApplication="./start.sh"

# http://wiki.bash-hackers.org/howto/getopts_tutorial
# https://unix.stackexchange.com/questions/129391/passing-named-arguments-to-shell-scripts
while getopts "h:n" opt; do
    case $opt in
        h)
            host="$OPTARG"
            ;;
        n)
            startApplication="echo 'no start application - remote'"
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

if [[ -z "$host" ]]; then
    echo "Please inform the host:"
    echo "$0 -h http://myip:8080"
    exit 1
else
    echo "Using host $host"
fi
exit 2

$startApplication

printf 'Waiting for application is ready'
until $(curl --output /dev/null --silent --head --fail $host:8080/application); do
    printf '.'
    sleep 0.1
done

printf '\n'
curl -v -w '\n%{time_total}\n' -X POST '$host:8080/event-reservations' -H 'Content-Type: application/json' -d '{"eventId": "uuid456", "limit": 90005}'
printf '\n'

export JAVA_OPTS_TEST="-Dhostname_test=$host:8080"
docker-compose --file docker-compose-test.yml up

./clean.sh
