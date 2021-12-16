package com.richard.wiki.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController // 返回字符串
// @Controller  返回一个页面
public class TestController {

    @Value("${test.hello:TEST}")
    private String testHello;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello() {
        return "Hello World" + testHello;
    }

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "Hello World！Post，" + name;
    }

}
