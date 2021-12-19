package com.richard.wiki.service;

import com.richard.wiki.domain.Ebook;
import com.richard.wiki.domain.EbookExample;
import com.richard.wiki.mapper.EbookMapper;
import com.richard.wiki.req.EbookReq;
import com.richard.wiki.resp.EbookResp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

    @Autowired
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req) {
        EbookExample ebookExample = new EbookExample();
        ebookExample.createCriteria().andNameLike("%" + req.getName() + "%");
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        List<EbookResp> ebookRespList = new ArrayList<>();
        for (Ebook ebook : ebookList) {
            EbookResp ebookReSq = new EbookResp();
            //ebookReSq.setName(ebook.getName());
            //ebookReSq.setDescription(ebook.getDescription());
            BeanUtils.copyProperties(ebook,ebookReSq);
            ebookRespList.add(ebookReSq);
        }

        return ebookRespList;
    }

}
