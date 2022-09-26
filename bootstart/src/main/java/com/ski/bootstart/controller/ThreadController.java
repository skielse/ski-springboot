package com.ski.bootstart.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.Callable;
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


    /**
     * 使用Callable
     */
    @GetMapping("callable")
    public Callable<String> callable() {
        log.info(LocalDateTime.now().toString() + "--->主线程开始");
        Callable<String> callable = () -> {
            String result = "return callable";
            // 执行业务耗时 3s
            Thread.sleep(3000);
            log.info(LocalDateTime.now().toString() + "--->子任务线程(" + Thread.currentThread().getName() + ")");
            return result;
        };
        log.info(LocalDateTime.now().toString() + "--->主线程结束");
        return callable;
    }

    public static String doBusiness() {
        // 执行业务耗时 10s
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return UUID.randomUUID().toString();
    }
}
