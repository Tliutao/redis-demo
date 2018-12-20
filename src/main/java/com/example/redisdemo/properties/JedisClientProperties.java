package com.example.redisdemo.properties;

import lombok.Data;

/**
 * @author 战猿点
 * @date 2018/5/14 14:23
 */
@Data
public class JedisClientProperties {

    private int port;
    private String hostName;
    private int timeout;

}
