package com.richard.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.richard.wiki.domain.Ebook;
import com.richard.wiki.domain.UserInfo;
import com.richard.wiki.domain.UserToken;
import com.richard.wiki.examples.EbookExample;
import com.richard.wiki.examples.UserTokenExample;
import com.richard.wiki.mapper.EbookMapper;
import com.richard.wiki.mapper.UserTokenMapper;
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
import org.springframework.util.CollectionUtils;
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

    @Autowired
    private WsService wsService;

    @Autowired
    private UserTokenMapper userTokenMapper;

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

        // ????????????
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
            ebookQueryResp.setDocCount(ebook.getDocCount());
            ebookQueryResp.setViewCount(ebook.getViewCount());
            ebookQueryResp.setVoteCount(ebook.getVoteCount());
            list.add(ebookQueryResp);
        }

        // ?????????????????????
        PageResp<EbookQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

    /**
     * ??????
     */
    public void save(EbookSaveReq req, UserInfo userInfo) {
        if (ObjectUtils.isEmpty(req.getId())) {
            // ??????
            Ebook ebook = Ebook.builder().id(snowFlake.nextId()).name(req.getName()).categoryId1(req.getCategory1Id()).categoryId2(req.getCategory2Id()).description(req.getDescription()).recordId(userInfo.getUserId()).recordName(userInfo.getUserName()).cover(req.getImgDirPath()).build();
            ebookMapper.insert(ebook);
        }else {
            // ?????????????????????record???????????????
            Ebook ebook = Ebook.builder().id(req.getId()).name(req.getName()).categoryId1(req.getCategory1Id()).categoryId2(req.getCategory2Id()).description(req.getDescription()).cover(req.getImgDirPath()).build();
            ebookMapper.updateByPrimaryKeySelective(ebook);
        }
    }

    /**
     * ??????
     * @param id ????????????id
     */
    public void delete(Long id) {
        ebookMapper.deleteByPrimaryKey(id);
    }

    /**
     * ????????????????????????????????????
     * @param id            ?????????id
     * @param userInfo      ???????????????????????????
     */
    public void vote(Long id,UserInfo userInfo) {
        // TODO:???????????????id?????????id???????????????????????????

        // ?????????????????????????????????
        Ebook ebookDb = ebookMapper.selectByPrimaryKey(id);
        // ??????????????????????????????????????????token
        UserTokenExample userTokenExample = new UserTokenExample();
        userTokenExample.createCriteria().andUserIdEqualTo(ebookDb.getRecordId());
        List<UserToken> userTokens = userTokenMapper.selectByExample(userTokenExample);
        if (CollectionUtils.isEmpty(userTokens)) {
            // ????????????????????????????????????
            LOG.info("??????????????????????????????");
        }

        // ??????????????????????????????TODO????????????????????????????????????????????????
        String userToken = userTokens.get(0).getAccessToken();
        wsService.sendMessageToUser(userToken,"???????????????" + ebookDb.getName() + "??????" + userInfo.getUserName() + "??????");
        System.out.println("??????");
    }

}
