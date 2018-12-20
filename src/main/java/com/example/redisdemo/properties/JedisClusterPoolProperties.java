package com.example.redisdemo.properties;

/**
 * @author 万明宇
 * @date 2018/5/14 14:25
 */
public class JedisClusterPoolProperties {

    private int maxTotal;
    private int maxIdle;
    private int maxWaitMillis;

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }
}
