package com.richard.wiki.service;

import com.richard.wiki.domain.Doc;
import com.richard.wiki.domain.DocExample;
import com.richard.wiki.mapper.DocMapper;
import com.richard.wiki.req.DocSaveReq;
import com.richard.wiki.resp.DocQueryResp;
import com.richard.wiki.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocService {

    @Autowired
    private DocMapper docMapper;

    @Autowired
    private SnowFlake snowFlake;

    /**
     * 返回所有文档级别
     * @return
     */
    public List<DocQueryResp> all() {
        DocExample docExample = new DocExample();
        docExample.setOrderByClause("sort asc");
        List<Doc> docList = docMapper.selectByExample(docExample);

        List<DocQueryResp> list = new ArrayList<>();
        for (Doc doc : docList) {
            DocQueryResp docQueryResp = new DocQueryResp();
            docQueryResp.setId(doc.getId());
            docQueryResp.setName(doc.getName());
            docQueryResp.setParent(doc.getParent());
            docQueryResp.setSort(doc.getSort());
            list.add(docQueryResp);
        }

        return list;
    }

    public void save(DocSaveReq req) {
        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            Doc doc = Doc.builder().id(snowFlake.nextId()).ebookId(req.getEbookId()).name(req.getName()).sort(req.getSort()).parent(req.getParent()).build();
            docMapper.insert(doc);
        }else {
            // 编辑
            Doc doc = Doc.builder().id(req.getId()).ebookId(req.getEbookId()).name(req.getName()).sort(req.getSort()).parent(req.getParent()).build();
            docMapper.updateByPrimaryKeySelective(doc);
        }
    }

    public void delete(List<String> idStr) {
        DocExample docExample = new DocExample();
        docExample.createCriteria().andIdIn(idStr);
        docMapper.deleteByExample(docExample);
    }

}
