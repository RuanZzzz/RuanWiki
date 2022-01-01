package com.richard.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.richard.wiki.domain.Ebook;
import com.richard.wiki.domain.EbookExample;
import com.richard.wiki.mapper.EbookMapper;
import com.richard.wiki.req.EbookQueryReq;
import com.richard.wiki.req.EbookSaveReq;
import com.richard.wiki.resp.EbookQueryResp;
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

    public PageResp<EbookQueryResp> list(EbookQueryReq ebookQueryReq) {
        EbookExample ebookExample = new EbookExample();
        if (!ObjectUtils.isEmpty(ebookQueryReq.getName())) {
            ebookExample.createCriteria().andNameLike("%" + ebookQueryReq.getName() + "%");
        }

        PageHelper.startPage(ebookQueryReq.getPage(), ebookQueryReq.getPageSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);

        // 列表复制
        //List<EbookResp> list = CopyUtil.copyList(ebookList,EbookResp.class);
        List<EbookQueryResp> list = new ArrayList<>();
        for (Ebook ebook : ebookList) {
            EbookQueryResp ebookQueryResp = new EbookQueryResp();
            ebookQueryResp.setId(ebook.getId());
            ebookQueryResp.setName(ebook.getName());
            ebookQueryResp.setDescription(ebook.getDescription());
            list.add(ebookQueryResp);
        }

        // 返回的分页参数
        PageResp<EbookQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

    /**
     * 保存
     */
    public void save(EbookSaveReq req) {

        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
        }else {
            Ebook ebook = Ebook.builder().id(req.getId()).name(req.getName()).build();
            ebookMapper.updateByPrimaryKey(ebook);
        }

    }

}
