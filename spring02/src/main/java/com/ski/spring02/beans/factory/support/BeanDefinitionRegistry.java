package com.ski.spring02.beans.factory.support;

import com.ski.spring02.beans.factory.config.BeanDefinition;

/**
 * @author wangzijie
 * @date 2021/10/13
 */
public interface BeanDefinitionRegistry {
    /**
     * 向注册表中注册 BeanDefinition
     *
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
