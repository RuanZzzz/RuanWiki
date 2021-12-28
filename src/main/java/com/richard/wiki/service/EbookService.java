package com.richard.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.richard.wiki.domain.Ebook;
import com.richard.wiki.domain.EbookExample;
import com.richard.wiki.mapper.EbookMapper;
import com.richard.wiki.req.Req;
import com.richard.wiki.resp.EbookResp;
import com.richard.wiki.resp.PageResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Autowired
    private EbookMapper ebookMapper;

    public PageResp<EbookResp> list(Req req) {
        EbookExample ebookExample = new EbookExample();
        if (!ObjectUtils.isEmpty(req.getName())) {
            ebookExample.createCriteria().andNameLike("%" + req.getName() + "%");
        }

        PageHelper.startPage(req.getPage(),req.getPageSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);

        // 列表复制
        //List<EbookResp> list = CopyUtil.copyList(ebookList,EbookResp.class);
        List<EbookResp> list = new ArrayList<>();
        for (Ebook ebook : ebookList) {
            EbookResp ebookResp = new EbookResp();
            ebookResp.setId(ebook.getId());
            ebookResp.setName(ebook.getName());
            ebookResp.setDescription(ebook.getDescription());
            list.add(ebookResp);
        }

        // 返回的分页参数
        PageResp<EbookResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

}
