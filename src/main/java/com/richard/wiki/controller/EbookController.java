package com.richard.wiki.controller;

import com.richard.wiki.req.EbookQueryReq;
import com.richard.wiki.req.EbookSaveReq;
import com.richard.wiki.resp.CommonResp;
import com.richard.wiki.resp.EbookQueryResp;
import com.richard.wiki.resp.PageResp;
import com.richard.wiki.service.EbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/ebook")
public class EbookController extends BaseController {

    @Autowired
    private EbookService ebookService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public CommonResp list(@Valid EbookQueryReq ebookQueryReq) {
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
        PageResp<EbookQueryResp> list = ebookService.list(ebookQueryReq);
        resp.setContent(list);

        return resp;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public CommonResp save(@Valid @RequestBody EbookSaveReq req) {
        CommonResp resp = new CommonResp<>();
        ebookService.save(req,this.getCurrentUser());
        return resp;
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp();
        ebookService.delete(id);
        return resp;
    }

}
