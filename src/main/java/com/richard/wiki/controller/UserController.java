package com.richard.wiki.controller;

import com.richard.wiki.domain.UserInfo;
import com.richard.wiki.req.UserLoginReq;
import com.richard.wiki.req.UserQueryReq;
import com.richard.wiki.req.UserResetPasswordReq;
import com.richard.wiki.req.UserSaveReq;
import com.richard.wiki.resp.CommonResp;
import com.richard.wiki.resp.PageResp;
import com.richard.wiki.resp.UserLoginResp;
import com.richard.wiki.resp.UserQueryResp;
import com.richard.wiki.service.UserService;
import com.richard.wiki.service.authorization.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResp list(@Valid UserQueryReq req) {
        CommonResp<PageResp<UserQueryResp>> resp = new CommonResp<>();
        PageResp<UserQueryResp> list = userService.list(req);
        resp.setContent(list);

        return resp;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CommonResp save(@Valid @RequestBody UserSaveReq req) {
        CommonResp resp = new CommonResp();
        userService.save(req);

        return resp;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp();
        userService.delete(id);

        return  resp;
    }

    @RequestMapping(value = "/reset-password",method = RequestMethod.POST)
    public CommonResp resetPassword(@Valid @RequestBody UserResetPasswordReq req) {
        CommonResp resp = new CommonResp();
        userService.resetPassword(req);

        return resp;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResp login(@Valid @RequestBody UserLoginReq req) {
        CommonResp resp = new CommonResp<>();
        // 登录成功返回用户信息和token
        resp.setContent(loginService.login(req));

        return resp;
    }

    @RequestMapping(value = "/logout/{token}")
    public CommonResp logout(@PathVariable String token) {
        CommonResp resp = new CommonResp();
        loginService.deleteToken(token);

        return resp;
    }

}
