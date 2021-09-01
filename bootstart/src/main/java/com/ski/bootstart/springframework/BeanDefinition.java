package com.ski.bootstart.springframework;

/**
 * @author wangzijie
 * @date 2021/9/1
 */
public class BeanDefinition {
    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
