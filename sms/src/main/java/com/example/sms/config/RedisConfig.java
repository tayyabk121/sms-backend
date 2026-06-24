package com.example.sms.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Configuration class for setting up Redis connection and RedisTemplate.
 * This class defines the necessary beans for connecting to a Redis server
 * and provides a RedisTemplate for performing Redis operations. It also
 * includes an event listener to verify the Redis connection when the application
 * context is refreshed.
 */
@Configuration
@Log4j2
@RequiredArgsConstructor
public class RedisConfig {
   
    /** Redis host address, injected from application properties. */
    @Value("${spring.redis.host}")
    private String redisHost;
    
    /** Redis port number, injected from application properties. */
    @Value("${spring.redis.port}")
    private int redisPort;
    
    /** Creates a LettuceConnectionFactory bean for connecting to the Redis server.
     * This method configures the connection factory with the specified host and port,
     * and logs the connection details for debugging purposes. */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        log.info(
                "Creating RedisConnectionFactory with host: {} and port: {}",
                redisHost, redisPort);
        
        return new LettuceConnectionFactory(
                new RedisStandaloneConfiguration(redisHost, redisPort));
    }
    
    /** Event listener that verifies the Redis connection when the application context is refreshed.
     * This method attempts to ping the Redis server using the connection factory and logs
     * the result. If the connection is successful, it logs a success message; if it fails,
     * it logs an error message with the details of the failure. */
    @EventListener(ContextRefreshedEvent.class)
    public void verifyRedisConnection(final ContextRefreshedEvent event) {
        try {
            RedisConnectionFactory factory = event.getApplicationContext()
                    .getBean(RedisConnectionFactory.class);
            
            String pong = factory.getConnection().ping();
            log.info("Successfully connected to Redis: {}", pong);
        } catch (Exception e) {
            log.error("Failed to connect to Redis at {}:{}. Error: {}",
                    redisHost, redisPort, e.getMessage());
        }
    }
    
    /** Creates a RedisTemplate bean for performing Redis operations.
     * This method configures the RedisTemplate with the provided connection factory
     * and sets the key and value serializers to StringRedisSerializer for both keys
     * and values, as well as for hash keys and hash values. It then initializes the
     * properties of the RedisTemplate and returns it for use in the application. */
    @Bean
    public RedisTemplate<String, String> redisTemplate(
            final RedisConnectionFactory connectionFactory){
     
        RedisTemplate<String,String> redisTemplate = new RedisTemplate<>();
        
        redisTemplate.setConnectionFactory(connectionFactory);
        
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        
        redisTemplate.afterPropertiesSet();
        
        return redisTemplate;
    }
    
}
