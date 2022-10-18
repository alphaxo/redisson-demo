package com.xjiaqing.redisson;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Redisson Demo test
 *
 */
public class RedissonDemo {

    private static final Logger logger = LogManager.getLogger(RedissonDemo.class);

    public static void main(String[] args) {

        logger.info("[RedissonDemo] redisson demo application start...");

        Config config = new Config();
        config.useReplicatedServers()
                .addNodeAddress("redis://redis-id.ng.0001.apne1.cache.amazonaws.com:6379");

        RedissonClient client = Redisson.create(config);

        while (true) {
            String k = "test:" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            RBucket<String> bucket = client.getBucket(k);
            bucket.set("xjiaqing");
            logger.info("set value to redis, key: {}, value: {}", k, "xjiaqing");

            try {
                Thread.sleep(60000);
            } catch (InterruptedException exception) {
                logger.warn(exception);
            }
        }
    }
}
