package com.ski.spring03.beans.factory.support;

import com.ski.spring03.beans.BeansException;
import com.ski.spring03.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author ski
 * @date 2021/12/21
 *
 * Bean实例化策略
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;

}
