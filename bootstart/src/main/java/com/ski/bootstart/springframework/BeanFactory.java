package com.ski.bootstart.springframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ski
 * @date 2021/9/1
 */
public class BeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }
}
