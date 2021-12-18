package com.richard.wiki.controller;

import com.richard.wiki.domain.Test;
import com.richard.wiki.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 返回字符串
// @Controller  返回一个页面
public class TestController {

    @Value("${test.hello:TEST}")
    private String testHello;

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello() {
        return "Hello World" + testHello;
    }

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "Hello World！Post，" + name;
    }

    @GetMapping("/test/list")
    public List<Test> list() {
        return testService.list();
    }

}
