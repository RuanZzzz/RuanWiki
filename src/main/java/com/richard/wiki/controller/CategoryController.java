package com.richard.wiki.controller;

import com.richard.wiki.req.CategoryQueryReq;
import com.richard.wiki.resp.CategoryQueryResp;
import com.richard.wiki.resp.CommonResp;
import com.richard.wiki.resp.PageResp;
import com.richard.wiki.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public CommonResp list(CategoryQueryReq categoryQueryReq) {
        CommonResp<PageResp<CategoryQueryResp>> resp = new CommonResp<>();
        PageResp<CategoryQueryResp> list = categoryService.list(categoryQueryReq);
        resp.setContent(list);

        return resp;
    }


}
