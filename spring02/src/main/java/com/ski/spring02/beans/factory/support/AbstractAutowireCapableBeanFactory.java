package com.ski.spring02.beans.factory.support;

import com.ski.spring02.beans.BeansException;
import com.ski.spring02.beans.factory.config.BeanDefinition;

/**
 * @author wangzijie
 * @date 2021/10/13
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }
}
