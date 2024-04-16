package com.ski.bootstart.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ski
 */
@Slf4j
@RestController
@RequestMapping("filter")
public class FilterController {

    @RequestMapping("test")
    public String test() {
        log.info("===========test");
        log.warn("===========test");
        log.error("===========test");
        throw new RuntimeException("wzj test ex");
    }

    @RequestMapping("test2")
    public Object test2() {
        log.info("===========test");
        log.warn("===========test");
        log.error("===========test");
        return "this is result";
    }
}
