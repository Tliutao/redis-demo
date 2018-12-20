package com.example.redisdemo.properties;

import java.util.List;

/**
 * Jedis集群配置类
 *
 * @author 战猿点
 */
public class JedisClusterConfigProperties {
    private int connectionTimeout;
    private int soTimeout;
    private int maxRedirections;
    private List<String> jedisClusterNodes;

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }

    public int getMaxRedirections() {
        return maxRedirections;
    }

    public void setMaxRedirections(int maxRedirections) {
        this.maxRedirections = maxRedirections;
    }

    public List<String> getJedisClusterNodes() {
        return jedisClusterNodes;
    }

    public void setJedisClusterNodes(List<String> jedisClusterNodes) {
        this.jedisClusterNodes = jedisClusterNodes;
    }
}
