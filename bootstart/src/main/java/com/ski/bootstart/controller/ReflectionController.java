package com.ski.bootstart.controller;

import lombok.extern.slf4j.Slf4j;
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
}
