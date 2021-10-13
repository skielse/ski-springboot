package com.ski.spring02.beans.factory;

import com.ski.spring02.beans.BeansException;

/**
 * @author wangzijie
 * @date 2021/10/13
 */
public interface BeanFactory {
    Object getBean(String name) throws BeansException;
}
