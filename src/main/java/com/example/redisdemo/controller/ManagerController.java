package com.example.redisdemo.controller;

import com.example.redisdemo.utils.StringTemplateRedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 万明宇
 * @date 2018/12/20 13:37
 */
@RestController
public class ManagerController {

    @Resource(name = "defaultStringTemplateRedisUtil")
    private StringTemplateRedisUtil defaultStringTemplateRedisUtil;

    @Resource(name = "StringRedisUtilCluster1")
    private StringTemplateRedisUtil StringRedisUtilCluster1;

    @RequestMapping("/test01")
    public void test01() {
        defaultStringTemplateRedisUtil.set("zyd","111111111111");

    }

    @RequestMapping("/test02")
    public void test02() {
        StringRedisUtilCluster1.set("zyd","2222222222222");

    }
}
