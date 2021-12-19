package com.richard.wiki.controller;

import com.richard.wiki.domain.Ebook;
import com.richard.wiki.service.EbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Autowired
    private EbookService ebookService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Ebook> list() {
        return ebookService.list();
    }

}
