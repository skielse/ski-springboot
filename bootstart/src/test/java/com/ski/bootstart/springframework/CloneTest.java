package com.ski.bootstart.springframework;

import com.google.gson.Gson;
import com.ski.bootstart.springframework.bean.CloneBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

/**
 * @author wangzijie
 * @date 2021/11/3
 */
@Slf4j
public class CloneTest {
    @Test
    public void test() {
        CloneBean source = new CloneBean();
        source.setId(1);
        CloneBean.PropertiesBean propertiesBean = new CloneBean.PropertiesBean();
        propertiesBean.setName("test");
        source.setProps(propertiesBean);

        CloneBean target = new CloneBean();
        BeanUtils.copyProperties(source, target);
        target = SerializationUtils.clone(source);
        target.getProps().setName("who");
        log.info(String.valueOf(source));
        Gson gson = new Gson();
        log.info(gson.toJson(source));
        log.info("==========分割线===========");
        log.info(String.valueOf(target));
        log.info(gson.toJson(target));
    }
}
