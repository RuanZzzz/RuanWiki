package com.richard.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.richard.wiki.domain.User;
import com.richard.wiki.domain.UserExample;
import com.richard.wiki.mapper.UserMapper;
import com.richard.wiki.req.UserQueryReq;
import com.richard.wiki.req.UserSaveReq;
import com.richard.wiki.resp.PageResp;
import com.richard.wiki.resp.UserQueryResp;
import com.richard.wiki.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SnowFlake snowFlake;

    /**
     * 用户列表返回
     * @param req 请求参数
     * @return 返回用户分页列表
     */
    public PageResp<UserQueryResp> list(UserQueryReq req) {
        UserExample userExample = new UserExample();
        UserExample.Criteria userCriteria = userExample.createCriteria();
        userExample.setOrderByClause("id desc");
        if (!ObjectUtils.isEmpty(req.getLoginName())) {
            userCriteria.andLoginNameLike("%" + req.getLoginName() + "%");
        }
        PageHelper.startPage(req.getPage(),req.getPageSize());
        List<User> userList = userMapper.selectByExample(userExample);
        PageInfo<User> pageInfo = new PageInfo<>(userList);

        // 组装返回的参数
        List<UserQueryResp> list = new ArrayList<>();
        for (User user : userList) {
            UserQueryResp userQueryResp = new UserQueryResp();
            userQueryResp.setId(user.getId());
            userQueryResp.setName(user.getName());
            userQueryResp.setLoginName(user.getLoginName());
            userQueryResp.setPassword(user.getPassword());
            list.add(userQueryResp);
        }

        PageResp<UserQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);

        return pageResp;
    }

    /**
     * 保存及更新
     * @param req 保存及更新的参数
     */
    public void save(UserSaveReq req) {
        if (ObjectUtils.isEmpty(req.getId())) {
            // 如果没有传id，则进行保存
            User user = User.builder().id(snowFlake.nextId()).loginName(req.getLoginName()).name(req.getName()).password(req.getPassword()).build();
            userMapper.insert(user);
        }else {
            // 如果传了id，则进行更新
            User user = User.builder().id(req.getId()).name(req.getName()).loginName(req.getLoginName()).password(req.getPassword()).build();
        }
    }

    public void delete(Long id) {
        userMapper.deleteByPrimaryKey(id);
    }

}
