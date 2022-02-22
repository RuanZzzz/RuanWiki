package com.richard.wiki.service;

import com.richard.wiki.domain.Content;
import com.richard.wiki.domain.Doc;
import com.richard.wiki.domain.DocExample;
import com.richard.wiki.exception.BusinessException;
import com.richard.wiki.exception.BusinessExceptionCode;
import com.richard.wiki.mapper.ContentMapper;
import com.richard.wiki.mapper.DocMapper;
import com.richard.wiki.mapper.DocMapperCust;
import com.richard.wiki.req.DocSaveReq;
import com.richard.wiki.resp.DocQueryResp;
import com.richard.wiki.util.RedisUtil;
import com.richard.wiki.util.RequestContext;
import com.richard.wiki.util.SnowFlake;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private DocMapperCust docMapperCust;

    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private WsService wsService;

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
            docQueryResp.setViewCount(doc.getViewCount());
            docQueryResp.setVoteCount(doc.getVoteCount());
            list.add(docQueryResp);
        }

        return list;
    }

    @Transactional
    public void save(DocSaveReq req) {
        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增文档
            Doc doc = Doc.builder().id(snowFlake.nextId()).ebookId(req.getEbookId()).name(req.getName()).sort(req.getSort()).viewCount(0).voteCount(0).parent(req.getParent()).build();
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
        // 文档阅读数+1
        docMapperCust.increaseViewCount(id);
        if (ObjectUtils.isEmpty(content)) {
            return "";
        }else {
            return content.getContent();
        }
    }

    public void vote(Long id) {
        // docMapperCust.increaseVoteCount(id);
        // 远程IP + doc.id 作为key，24小时内不能重复
        String key = RequestContext.getRemoteAddr();
        if (redisUtil.validateRepeat("DOC_VOTE_" + id + "_" + key, 3600 * 24)) {
            docMapperCust.increaseVoteCount(id);
        }else {
            throw new BusinessException(BusinessExceptionCode.VOTE_REPEAT);
        }

        // 推送消息
        Doc docDb = docMapper.selectByPrimaryKey(id);
        String logId = MDC.get("LOG_ID");
        wsService.sendInfo("【" + docDb.getName() + "】被点赞",logId);
    }

    public void updateEbookInfo() {
        docMapperCust.updateEbookInfo();
    }

}
