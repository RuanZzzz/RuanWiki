package com.richard.wiki.controller;

import com.richard.wiki.domain.Demo;
import com.richard.wiki.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Demo> list() {
        return demoService.list();
    }

}
