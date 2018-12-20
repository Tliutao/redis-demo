package com.example.redisdemo.properties;

import lombok.Data;

/**
 * 单机版properties
 * @author 战猿点
 * @date 2018/5/14 14:25
 */
@Data
public class JedisClientPoolProperties {

    private int maxTotal;
    private int maxIdle;
    private int maxWaitMillis;
}
