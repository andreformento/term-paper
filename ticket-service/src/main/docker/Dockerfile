FROM openjdk:8-jre-alpine
MAINTAINER Andre Formento <andreformento.sc@gmail.com>

VOLUME /tmp
ADD *.jar app.jar
ENV JAVA_OPTS=""
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
