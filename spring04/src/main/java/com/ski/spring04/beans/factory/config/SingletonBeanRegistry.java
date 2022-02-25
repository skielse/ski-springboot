package com.ski.spring04.beans.factory.config;

/**
 * @author wangzijie
 * @date 2022/2/25
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
