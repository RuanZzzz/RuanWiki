package com.richard.wiki.service;

import com.richard.wiki.domain.Ebook;
import com.richard.wiki.domain.EbookExample;
import com.richard.wiki.mapper.EbookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbookService {

    @Autowired
    private EbookMapper ebookMapper;

    public List<Ebook> list() {
        EbookExample ebookExample = new EbookExample();

        return ebookMapper.selectByExample(ebookExample);
    }

}
