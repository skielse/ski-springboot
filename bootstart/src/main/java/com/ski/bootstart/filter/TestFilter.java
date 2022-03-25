package com.ski.bootstart.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author wangzijie
 * @date 2022/3/25
 */
@Slf4j
@Component
@Order(1)
public class TestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            log.info("11111----doFilter");
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            log.error("catch e:", e);
        }
    }
}
