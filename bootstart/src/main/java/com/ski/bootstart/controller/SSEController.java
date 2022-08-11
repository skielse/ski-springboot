package com.ski.bootstart.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangzijie
 * @date 2022/8/11
 */
@Slf4j
@RestController
@RequestMapping("sse")
public class SSEController {
    private static Map<String, SseEmitter> sseCache = new ConcurrentHashMap<>();

    @GetMapping(path = "")
    public String index() {
        return "sse_index.html";
    }

    @ResponseBody
    @GetMapping(path = "subscribe", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public SseEmitter push(String id) throws IOException {
        // 超时时间设置为3s，用于演示客户端自动重连
        SseEmitter sseEmitter = new SseEmitter(30000L);
        // 设置前端的重试时间为1s
        sseEmitter.send(SseEmitter.event().reconnectTime(10000).data("连接成功"));
        sseCache.put(id, sseEmitter);
        log.info("add " + id);
        sseEmitter.onTimeout(() -> {
            log.info(id + "超时");
            sseCache.remove(id);
        });
        sseEmitter.onCompletion(() -> log.info("完成！！！"));
        return sseEmitter;
    }

    @GetMapping(path = "push")
    public String push(String id, String content) throws IOException {
        SseEmitter sseEmitter = sseCache.get(id);
        if (sseEmitter != null) {
            sseEmitter.send(content);
        }
        return "over";
    }

    @GetMapping(path = "over")
    public String over(String id) {
        SseEmitter sseEmitter = sseCache.get(id);
        if (sseEmitter != null) {
            sseEmitter.complete();
            sseCache.remove(id);
        }
        return "over";
    }
}
