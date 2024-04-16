package com.ski.spring04.beans.factory.config;

/**
 * @author ski
 * @date 2022/2/25
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

}
