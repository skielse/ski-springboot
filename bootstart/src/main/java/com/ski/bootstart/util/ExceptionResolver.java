package com.ski.bootstart.util;

import com.google.gson.Gson;
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
        log.info("into ExceptionResolver:", e);
        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter writer = response.getWriter();
            Gson gson = new Gson();
            Map<Object, Object> map = new HashMap<>(1);
            map.put("ex", e.getMessage());
            writer.write( gson.toJson(map));
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            log.error("exceptionResolver ioe:", ex);
        }
        //直接返回,流程结束
         return new ModelAndView();
        //不指定mv会继续走流程,虽然已经输出流给请求端,会触发TestFilter的try catch
        //return null;
    }
}
