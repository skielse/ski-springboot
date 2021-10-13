package com.ski.spring02.beans.factory.support;

import com.ski.spring02.beans.BeansException;
import com.ski.spring02.beans.factory.BeanFactory;
import com.ski.spring02.beans.factory.config.BeanDefinition;

/**
 * @author wangzijie
 * @date 2021/10/13
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

}
