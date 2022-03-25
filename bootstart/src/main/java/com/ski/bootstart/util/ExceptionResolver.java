package com.ski.bootstart.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangzijie
 * @date 2022/3/25
 */
@Slf4j
@Component
public class ExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o, Exception e) {
        log.error("into ExceptionResolver:", e);
        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter writer = response.getWriter();

            Map map = new HashMap<>();
            map.put("ex", e.getMessage());
            writer.write(map.toString());
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            log.error("exceptionResolver ioe:", ex);
        }
        return new ModelAndView();
    }
}
