package com.richard.wiki.websocket;

import com.richard.wiki.examples.UserTokenExample;
import com.richard.wiki.mapper.UserTokenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;

@Component
@ServerEndpoint("/ws/{token}")
public class WebSocketServer {

    private static final Logger LOG = LoggerFactory.getLogger(WebSocketServer.class);

    private static UserTokenMapper userTokenMapper;
    public static void setUserTokenMapper(UserTokenMapper userTokenMapper) {
        WebSocketServer.userTokenMapper = userTokenMapper;
    }

    /**
     * 每个客户端一个token（使用用户的token）
     */
    private String token = "";

    private static HashMap<String,Session> map = new HashMap<>();

    /**
     * 连接成功
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {
        map.put(token, session);
        this.token = token;
        LOG.info("有新连接：token：{}，session id：{}，当前连接数：{}", token, session.getId(), map.size());
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose(Session session) {
        UserTokenExample delTokenExample = new UserTokenExample();
        delTokenExample.createCriteria().andAccessTokenEqualTo(this.token);
        userTokenMapper.deleteByExample(delTokenExample);

        map.remove(this.token);
        LOG.info("连接关闭，token：{}，session id：{}！当前连接数：{}", this.token, session.getId(), map.size());
    }

    /**
     * 收到消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        LOG.info("收到消息：{}，内容：{}", token, message);
    }

    /**
     * 连接错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        LOG.error("发生错误", error);
    }

    /**
     * 群发消息
     */
    public void sendInfo(String message) {
        for (String token : map.keySet()) {
            Session session = map.get(token);
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                LOG.error("推送消息失败：{}，内容：{}", token, message);
            }
            LOG.info("推送消息：{}，内容：{}", token, message);
        }
    }

    /**
     * 发送消息给指定用户
     * @param token     用户连接的token
     * @param message   发送的消息
     */
    public void sendMessageToUser(String token,String message) {
        if (map.get(token) == null) {
            LOG.error("用户不存在,或者用户已断开连接");
        }
        Session session = map.get(token);
        LOG.info("推送消息给 {}，内容为：{}", token, message);

        try {
            session.getBasicRemote().sendText(message);
        }catch (IOException e) {
            LOG.error("推送消息给 {} 失败，内容为：{}", token, message);
        }

        LOG.info("推送给 {} 成功，内容为：{}", token, message);
    }

    /**
     * 发送消息给所有用户（后续使用该方法替代sendInfo）
     * @param message       发送的消息
     */
    public void sendMessageToAllUser(String message) {
        for (String token : map.keySet()) {
            Session session = map.get(token);
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                LOG.error("推送消息失败：{}，内容：{}", token, message);
            }
            LOG.info("推送消息：{}，内容：{}", token, message);
        }
    }

}
