package com.ski.spring03.beans.factory.config;

/**
 * @author ski
 * @date 2021/12/21
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

}
