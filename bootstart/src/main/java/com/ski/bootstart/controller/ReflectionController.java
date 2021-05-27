package com.ski.bootstart.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzijie
 * @date 2021/3/15
 */
@Slf4j
@RestController
@RequestMapping("/reflection")
public class ReflectionController {

    @RequestMapping("/class")
    public Object classInfo() {
        Class<ReflectionController> aClass = ReflectionController.class;
        log.info("class is {}", aClass);
        return aClass;
    }

    private String getStringTimeBySecond(Integer playerTime) {
        //时
        int h = (playerTime / 1000) / 3600;
        //分钟
        int m = ((playerTime / 1000) % 3600) / 60;
        //秒
        int s = (playerTime / 1000) % 60;

        return StringUtils.join(h < 10 ? "0" + h : h, ":", m < 10 ? "0" + m : m, ":", s < 10 ? "0" + s : s);
    }
}
