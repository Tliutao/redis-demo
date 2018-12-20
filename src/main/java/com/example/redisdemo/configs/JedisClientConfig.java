package com.example.redisdemo.configs;

import com.example.redisdemo.properties.JedisClientPoolProperties;
import com.example.redisdemo.properties.JedisClientProperties;
import com.example.redisdemo.utils.RedisUtil;
import com.example.redisdemo.utils.StringTemplateRedisUtil;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis单机版配置
 * @author 战猿点
 * @date 2018/5/11 15:50
 */
@Configuration
public class JedisClientConfig {

    @Bean
    @ConfigurationProperties(prefix = "com.redis.only.pool")
    public JedisClientPoolProperties jedisClientPoolProperties(){
        return new JedisClientPoolProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "com.redis.only.client")
    public JedisClientProperties jedisClientProperties(){
        return new JedisClientProperties();
    }

    /**
     * 单节点
     * @return
     */
    @Bean
    public RedisConnectionFactory defaultJedisConnectionFactory(){
        //如果什么参数都不设置，默认连接本地6379端口
        JedisClientPoolProperties jedisClientPoolProperties = jedisClientPoolProperties();
        JedisClientProperties jedisClientProperties = jedisClientProperties();
        JedisConnectionFactory factory = new JedisConnectionFactory();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(jedisClientPoolProperties.getMaxIdle());
        jedisPoolConfig.setMaxTotal(jedisClientPoolProperties.getMaxTotal());
        jedisPoolConfig.setMaxWaitMillis(jedisClientPoolProperties.getMaxWaitMillis());

        factory.setPort(jedisClientProperties.getPort());
        factory.setHostName(jedisClientProperties.getHostName());
        factory.setTimeout(jedisClientProperties.getTimeout());
        factory.setPoolConfig(jedisPoolConfig);
        return factory;
    }

    @Bean
    public RedisTemplate<String,Object> defaultRedisTemplate(RedisConnectionFactory defaultJedisConnectionFactory){
        //创建一个模板类
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        //将刚才的redis连接工厂设置到模板类中
        template.setConnectionFactory(defaultJedisConnectionFactory);
        StringRedisSerializer hashKeySerializer = new StringRedisSerializer();
        template.setKeySerializer(hashKeySerializer);
        template.setHashKeySerializer(hashKeySerializer);
        return template;
    }

    @Bean
    public StringRedisTemplate defaultStringRedisTemplate(RedisConnectionFactory defaultJedisConnectionFactory){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(defaultJedisConnectionFactory);
        return stringRedisTemplate;
    }

    /**
     * 序列化不同(对象)
     * @param defaultRedisTemplate
     * @return
     */
    @Bean
    public RedisUtil defaultRedisUtil(RedisTemplate<String,Object> defaultRedisTemplate){
        return new RedisUtil(defaultRedisTemplate);
    }

    /**
     * (可视的数据)
     * @param defaultStringRedisTemplate
     * @return
     */
    @Bean
    public StringTemplateRedisUtil defaultStringTemplateRedisUtil(StringRedisTemplate defaultStringRedisTemplate){
        return new StringTemplateRedisUtil(defaultStringRedisTemplate);
    }

}
