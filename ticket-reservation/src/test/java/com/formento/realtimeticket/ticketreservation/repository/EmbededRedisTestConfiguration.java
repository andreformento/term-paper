package com.formento.realtimeticket.ticketreservation.repository;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmbededRedisTestConfiguration {

    private final redis.embedded.RedisServer redisServer;

    public EmbededRedisTestConfiguration(@Value("${spring.redis.port}") final int redisPort) throws IOException {
        this.redisServer = new redis.embedded.RedisServer(redisPort);
    }

    @PostConstruct
    public void startRedis() {
        // ps -ef | grep -i 'redis-server'
        // kill -9 PID owned by redis
        this.redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        this.redisServer.stop();
    }

}
