package com.ski.spring03.beans.factory.support;

import com.ski.spring03.beans.factory.config.BeanDefinition;

/**
 * @author ski
 * @date 2021/12/21
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
