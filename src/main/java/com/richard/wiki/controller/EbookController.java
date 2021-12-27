package com.richard.wiki.controller;

import com.richard.wiki.req.Req;
import com.richard.wiki.resp.CommonResp;
import com.richard.wiki.resp.EbookResp;
import com.richard.wiki.resp.PageResp;
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
    public CommonResp list(Req req) {
        CommonResp<PageResp<EbookResp>> resp = new CommonResp<>();
        PageResp<EbookResp> list = ebookService.list(req);
        resp.setContent(list);

        return resp;
    }

}
