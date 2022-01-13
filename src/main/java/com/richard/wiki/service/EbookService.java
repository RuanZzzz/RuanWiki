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
import com.richard.wiki.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {
    @Value("${server.name}")
    private String serverName;

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Autowired
    private EbookMapper ebookMapper;

    @Autowired
    private SnowFlake snowFlake;

    public PageResp<EbookQueryResp> list(EbookQueryReq ebookQueryReq) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria ebookCriteria = ebookExample.createCriteria();
        ebookExample.setOrderByClause("id desc");
        if (!ObjectUtils.isEmpty(ebookQueryReq.getName())) {
            ebookCriteria.andNameLike("%" + ebookQueryReq.getName() + "%");
        }
        if (!ObjectUtils.isEmpty(ebookQueryReq.getCategoryId2())) {
            ebookCriteria.andCategoryId2EqualTo(ebookQueryReq.getCategoryId2());
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
            ebookQueryResp.setCover(this.serverName + ebook.getCover());
            ebookQueryResp.setCategory1Id(ebook.getCategoryId1());
            ebookQueryResp.setCategory2Id(ebook.getCategoryId2());
            ebookQueryResp.setImgName(ebook.getCover());
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
            Ebook ebook = Ebook.builder().id(snowFlake.nextId()).name(req.getName()).categoryId1(req.getCategory1Id()).categoryId2(req.getCategory2Id()).description(req.getDescription()).cover(req.getImgDirPath()).build();
            ebookMapper.insert(ebook);
        }else {
            Ebook ebook = Ebook.builder().id(req.getId()).name(req.getName()).categoryId1(req.getCategory1Id()).categoryId2(req.getCategory2Id()).description(req.getDescription()).cover(req.getImgDirPath()).build();
            ebookMapper.updateByPrimaryKeySelective(ebook);
        }
    }

    /**
     * 删除
     * @param id 待删除的id
     */
    public void delete(Long id) {
        ebookMapper.deleteByPrimaryKey(id);
    }

}
