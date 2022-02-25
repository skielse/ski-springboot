package com.ski.spring04.beans.factory;

import com.ski.spring04.beans.BeansException;

/**
 * @author wangzijie
 * @date 2022/2/25
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;

}
