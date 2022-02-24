package com.richard.wiki.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {

    /**
     * userId       用户id
     * userName     用户昵称
     * loginName    登录名
     * avatar       头像（目前还没有）
     */
    private String userId;
    private String userName;
    private String loginName;
    private String avatar;

}
