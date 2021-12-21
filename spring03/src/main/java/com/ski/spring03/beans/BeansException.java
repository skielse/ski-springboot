package com.ski.spring03.beans;

/**
 * @author wangzijie
 * @date 2021/12/21
 */
public class BeansException extends RuntimeException{
    private static final long serialVersionUID = 8654144774805653798L;

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
