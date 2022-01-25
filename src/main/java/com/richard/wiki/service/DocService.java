package com.richard.wiki.service;

import com.richard.wiki.domain.Content;
import com.richard.wiki.domain.Doc;
import com.richard.wiki.domain.DocExample;
import com.richard.wiki.mapper.ContentMapper;
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
    private ContentMapper contentMapper;

    @Autowired
    private SnowFlake snowFlake;

    /**
     * 返回所有文档级别
     * @return
     */
    public List<DocQueryResp> all(Long ebookId) {
        DocExample docExample = new DocExample();
        docExample.createCriteria().andEbookIdEqualTo(ebookId);
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


            // 新增文档
            Doc doc = Doc.builder().id(snowFlake.nextId()).ebookId(req.getEbookId()).name(req.getName()).sort(req.getSort()).parent(req.getParent()).build();
            docMapper.insert(doc);

            // 新增文档的内容
            Content content = Content.builder().id(doc.getId()).content(req.getContent()).build();
            contentMapper.insert(content);
        }else {
            // 编辑文档
            Doc doc = Doc.builder().id(req.getId()).ebookId(req.getEbookId()).name(req.getName()).sort(req.getSort()).parent(req.getParent()).build();
            docMapper.updateByPrimaryKeySelective(doc);

            // 编辑文档内容
            Content content = Content.builder().id(req.getId()).content(req.getContent()).build();
            int cnt = contentMapper.updateByPrimaryKeyWithBLOBs(content);
            if (cnt == 0) {
                contentMapper.insert(content);
            }
        }
    }

    public void delete(List<String> idStr) {
        DocExample docExample = new DocExample();
        docExample.createCriteria().andIdIn(idStr);
        docMapper.deleteByExample(docExample);
    }

    public String findContent(Long id) {
        Content content = contentMapper.selectByPrimaryKey(id);
        return ObjectUtils.isEmpty(content.getContent()) ? "" : content.getContent();
    }

}
