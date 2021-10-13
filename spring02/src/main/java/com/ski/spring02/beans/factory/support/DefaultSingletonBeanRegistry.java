package com.ski.spring02.beans.factory.support;

import com.ski.spring02.beans.BeansException;
import com.ski.spring02.beans.factory.config.BeanDefinition;
import com.ski.spring02.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangzijie
 * @date 2021/10/13
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
