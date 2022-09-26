package com.ski.bootstart.controller;

import com.ski.bootstart.util.WebsocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author wangzijie
 * @date 2021/9/7
 */
@Slf4j
@RestController
@RequestMapping("ws")
public class WebsocketController {

    @RequestMapping("test")
    public String test() {
        String refText = "where are you from";
        String voiceUrl = "/Users/wangzijie/Downloads/0d778f1a434c4451a54907b753e964ab.mp3";
        String type = "sent.eval";
        BlockingQueue<String> queue = new SynchronousQueue<>(true);
        try {
            WebsocketUtil.STWebsocketAPI(voiceUrl, refText, type, message -> {
                try {
                    queue.put(message);
                } catch (Exception e) {
                    log.error("", e);
                }
            });
            return queue.poll(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("", e);
        }
        return "no message";
    }
}
