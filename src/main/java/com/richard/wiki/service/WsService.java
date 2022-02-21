package com.richard.wiki.service;

import com.richard.wiki.domain.Doc;
import com.richard.wiki.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class WsService {

    @Autowired
    private WebSocketServer webSocketServer;

    @Async
    public void sendInfo(String message) {

        // 推送消息
        webSocketServer.sendInfo(message);
    }

}
