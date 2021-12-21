package com.ski.spring03.beans.factory;

import com.ski.spring03.beans.BeansException;

/**
 * @author wangzijie
 * @date 2021/12/21
 */
public interface BeanFactory {
    Object getBean(String name) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;
}
