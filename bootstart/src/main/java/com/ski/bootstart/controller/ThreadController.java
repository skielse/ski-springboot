package com.ski.bootstart.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author wangzijie
 * @date 2022/8/26
 */
@Slf4j
@RestController
@RequestMapping("thread")
public class ThreadController {

    @RequestMapping("sleep")
    public String sleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            log.error("", e);
        }
        return "test";
    }
}
