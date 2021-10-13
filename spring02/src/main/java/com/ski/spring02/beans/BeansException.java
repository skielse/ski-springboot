package com.ski.spring02.beans;

/**
 * 定义 Bean 异常
 *
 * @author wangzijie
 * @date 2021/10/13
 */
public class BeansException extends RuntimeException {
    private static final long serialVersionUID = 4330836077498096821L;

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
