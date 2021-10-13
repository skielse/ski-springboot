package com.ski.spring02;

import com.ski.spring02.bean.UserService;
import com.ski.spring02.beans.factory.config.BeanDefinition;
import com.ski.spring02.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * @author wangzijie
 * @date 2021/10/13
 */
public class ApiTest {
    @Test
    public void test_BeanFactory(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.第一次获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        // 4.第二次获取 bean from Singleton
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();
    }

}
