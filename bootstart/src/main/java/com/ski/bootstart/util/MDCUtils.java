package com.ski.bootstart.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * @author wangzijie
 * @date 2021/7/13
 */
public class MDCUtils {
    public MDCUtils() {
    }

    public static String get(String key) {
        String value = null;

        try {
            if (StringUtils.isNotBlank(key)) {
                value = MDC.get(key);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return value;
    }

    public static String get(String key, String defaultValue) {
        String value = null;

        try {
            if (StringUtils.isNotBlank(key)) {
                value = MDC.get(key);
                value = value != null ? value : defaultValue;
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return value;
    }

    public static void put(String key, String value) {
        try {
            if (StringUtils.isNotEmpty(key) && value != null) {
                MDC.put(key, value);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public static Map<String, String> getCopyMDC() {
        try {
            return MDC.getCopyOfContextMap();
        } catch (Exception var1) {
            var1.printStackTrace();
            return null;
        }
    }

    public static void setMDC(Map<String, String> mdc) {
        try {
            if (!CollectionUtils.isEmpty(mdc)) {
                MDC.setContextMap(mdc);
            }
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public static void remove(String key) {
        try {
            if (StringUtils.isNotBlank(key)) {
                MDC.remove(key);
            }
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public static void clear() {
        try {
            MDC.clear();
        } catch (Exception var1) {
            var1.printStackTrace();
        }

    }
}
