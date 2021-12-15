package com.richard.wiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController // 返回字符串
// @Controller  返回一个页面
public class TestController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello() {
        return "Hello World";
    }

}
