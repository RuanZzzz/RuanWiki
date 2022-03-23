package com.richard.wiki.service;

import com.richard.wiki.domain.Doc;
import com.richard.wiki.websocket.WebSocketServer;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class WsService {

    @Autowired
    private WebSocketServer webSocketServer;

    @Async
    public void sendInfo(String message,String logId) {
        MDC.put("LOG_ID",logId);
        // 推送消息
        webSocketServer.sendInfo(message);
    }

    @Async
    public void sendMessageToUser(String token, String message) {
        webSocketServer.sendMessageToUser(token,message);
    }

}
