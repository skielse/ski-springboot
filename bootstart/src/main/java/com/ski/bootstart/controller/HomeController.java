package com.ski.bootstart.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 *
 * @author wangzijie
 * @date 2020/7/21
 */
@Slf4j
@RestController
@RequestMapping("home")
public class HomeController {

    @RequestMapping("index")
    public String index() {
      log.info("============= Welcome back home =============");
      return "============= Welcome back home =============";
    }
}
