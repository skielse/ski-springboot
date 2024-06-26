package com.ski.spring02.beans.factory.config;

/**
 * @author ski
 * @date 2021/10/13
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
}
