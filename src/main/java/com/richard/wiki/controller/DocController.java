package com.richard.wiki.controller;

import com.richard.wiki.req.DocSaveReq;
import com.richard.wiki.resp.CommonResp;
import com.richard.wiki.resp.DocQueryResp;
import com.richard.wiki.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocController {

    @Autowired
    private DocService docService;

    /**
     * 返回文档所有信息
     * @return
     */
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    private CommonResp all() {
        CommonResp<List<DocQueryResp>> resp = new CommonResp<>();
        List<DocQueryResp> list = docService.all();
        resp.setContent(list);

        return resp;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    private CommonResp save(@RequestBody DocSaveReq req) {
        CommonResp resp = new CommonResp();
        docService.save(req);
        return resp;
    }

}
