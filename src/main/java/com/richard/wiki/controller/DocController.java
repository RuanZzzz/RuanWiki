package com.richard.wiki.controller;

import com.richard.wiki.req.DocSaveReq;
import com.richard.wiki.resp.CommonResp;
import com.richard.wiki.resp.DocQueryResp;
import com.richard.wiki.resp.PageResp;
import com.richard.wiki.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
    @RequestMapping(value = "/all/{ebookId}",method = RequestMethod.GET)
    private CommonResp all(@PathVariable Long ebookId) {
        CommonResp<List<DocQueryResp>> resp = new CommonResp<>();
        List<DocQueryResp> list = docService.all(ebookId);
        resp.setContent(list);

        return resp;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    private CommonResp save(@RequestBody DocSaveReq req) {
        CommonResp resp = new CommonResp();
        docService.save(req);
        return resp;
    }

    @RequestMapping(value = "/delete/{idStr}",method = RequestMethod.DELETE)
    public CommonResp delete(@PathVariable String idStr) {
        CommonResp resp = new CommonResp();
        List<String> list =  Arrays.asList(idStr.split(","));
        docService.delete(list);
        return resp;
    }

    @RequestMapping(value = "/find-content/{id}",method = RequestMethod.GET)
    public CommonResp findContent(@PathVariable Long id) {
        CommonResp<String> resp = new CommonResp<>();
        String content = docService.findContent(id);
        resp.setContent(content);
        return resp;
    }

}
