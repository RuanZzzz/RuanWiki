package com.richard.wiki.service;

import com.richard.wiki.domain.Demo;
import com.richard.wiki.domain.DemoExample;
import com.richard.wiki.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoService {

    @Autowired
    private DemoMapper demoMapper;

    public List<Demo> list() {
        DemoExample demoExample = new DemoExample();

        return demoMapper.selectByExample(demoExample);
    }

}
