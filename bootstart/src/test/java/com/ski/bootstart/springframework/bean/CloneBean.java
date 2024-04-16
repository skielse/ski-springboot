package com.ski.bootstart.springframework.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ski
 * @date 2021/11/3
 */
@Data
public class CloneBean implements Serializable {
    private static final long serialVersionUID = -9056796884524089526L;
    Integer id;

    PropertiesBean props;

    @Data
    public static class PropertiesBean implements Serializable {
        private static final long serialVersionUID = -8147910800710091767L;
        String name;
    }
}
