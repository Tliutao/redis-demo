package com.example.redisdemo.configs;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.example.redisdemo.properties.JedisClusterConfigProperties;
import com.example.redisdemo.properties.JedisClusterPoolProperties;
import com.example.redisdemo.utils.RedisUtil;
import com.example.redisdemo.utils.StringTemplateRedisUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 集群版
 * @author 万明宇(战猿点)
 * @date 2018/7/29 18:03
 */
@Configuration
public class JedisClusterConfig {

    @Bean
    @ConfigurationProperties(prefix = "com.wan.redis.pool")
    public JedisClusterPoolProperties jedisClusterPoolProperties(){
        return new JedisClusterPoolProperties();
    }
    @Bean
    @ConfigurationProperties(prefix = "com.wan.redis.cluster")
    public JedisClusterConfigProperties jedisClusterConfigProperties(){
        return new JedisClusterConfigProperties();
    }



    @Bean
    public RedisConnectionFactory jedisConnectionFactoryCluster1(){
        JedisClusterPoolProperties jedisClusterPoolProperties = jedisClusterPoolProperties();
        JedisClusterConfigProperties jedisClusterConfigProperties = jedisClusterConfigProperties();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(jedisClusterPoolProperties.getMaxIdle());
        jedisPoolConfig.setMaxTotal(jedisClusterPoolProperties.getMaxTotal());
        jedisPoolConfig.setMaxWaitMillis(jedisClusterPoolProperties.getMaxWaitMillis());

        //集群配置
        RedisClusterConfiguration configuration = new RedisClusterConfiguration(jedisClusterConfigProperties.getJedisClusterNodes());
        configuration.setMaxRedirects(jedisClusterConfigProperties.getMaxRedirections());

        JedisConnectionFactory factory = new JedisConnectionFactory(configuration, jedisPoolConfig);
        factory.setTimeout(jedisClusterConfigProperties.getSoTimeout());
        return factory;
    }

    /**
     * redisClusterTemplateCluster1
     * @param jedisConnectionFactoryCluster1 参数名为beanName
     * @return
     */
    @Bean
    public RedisTemplate<String,Object> redisClusterTemplateCluster1(RedisConnectionFactory jedisConnectionFactoryCluster1){
        //创建一个模板类
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        //将刚才的redis连接工厂设置到模板类中
        template.setConnectionFactory(jedisConnectionFactoryCluster1);
        //初始化redis key中的序列化方式 StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        //初始化redis value中的序列化方式 fastjson序列化
        GenericFastJsonRedisSerializer serializer = new GenericFastJsonRedisSerializer();
        template.setHashValueSerializer(serializer);
        template.setValueSerializer(serializer);
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplateCluster1(RedisConnectionFactory jedisConnectionFactoryCluster1){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(jedisConnectionFactoryCluster1);
        return  stringRedisTemplate;
    }

    /**
     * 序列化不同(对象)
     * @param redisClusterTemplateCluster1
     * @return
     */
    @Bean
    public RedisUtil redisUtilCluster1(RedisTemplate<String,Object> redisClusterTemplateCluster1){
        return new RedisUtil(redisClusterTemplateCluster1);
    }


    /**
     * (可视的数据)
     * @param stringRedisTemplateCluster1
     * @return
     */
    @Bean
    public StringTemplateRedisUtil StringRedisUtilCluster1(StringRedisTemplate stringRedisTemplateCluster1){
        return new StringTemplateRedisUtil(stringRedisTemplateCluster1);
    }


}
