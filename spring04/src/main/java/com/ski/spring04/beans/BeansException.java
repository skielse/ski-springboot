package com.ski.spring04.beans;

/**
 * @author wangzijie
 * @date 2022/2/25
 */
public class BeansException extends RuntimeException {

    private static final long serialVersionUID = 4718663406665378308L;

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
