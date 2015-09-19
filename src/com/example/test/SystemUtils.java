/**
 * @(#)SystemUtils.java    v1.0 2012-12-31
 * 
 * Copyright (c) 2012-2012  yunos, Inc. 
 * 2 zijinghua Road, HangZhou, C.N 
 * All rights reserved. 
 */
package com.example.test;

import java.lang.reflect.Method;
import java.util.Locale;

/**
 * 
 */
public class SystemUtils {
    public static final int ZH_CN = 0;
    public static final int ZH_TW = 1;
    public static final int EN_GB = 2;
    public static final int EN_US = 3;
    
    public static int getLanguageEnv() {
        Locale l = Locale.getDefault();
        String language = l.getLanguage();
        String country = l.getCountry().toLowerCase(Locale.getDefault());
        if ("zh".equals(language)) {
            if ("cn".equals(country)) {
                return ZH_CN;
            } else if ("tw".equals(country)) {
                return ZH_TW;
            }
        } else if ("en".equals(language)) {
            if ("gb".equals(country)) {
               return EN_GB;
            } else if ("us".equals(country)) {
                return EN_US;
            }
        }
        return EN_GB;
    }
    
   
    private static Boolean isYunos = null;
    public static boolean isYunOS() {
        if (null != isYunos)
            return isYunos;
        //TODO remove the hard code below
//        isYunos = true;
        isYunos = isYunos();
        return isYunos;
    }
    
    private static boolean isYunos() {
        String yunosStr = "0";
        // for yunos, add this flag in ActivityManagerService
        yunosStr = getSystemProperties("persist.sys.yunosflag");
        return yunosStr.equals("1");
    }

    private static String getSystemProperties(String key) {
        String value = null;
        Class<?> cls = null;
        ;
        try {
            cls = Class.forName("android.os.SystemProperties");
            Method hideMethod = cls.getMethod("get", String.class);
            Object object = cls.newInstance();
            value = (String) hideMethod.invoke(object, key);
        } catch (Exception e) {
        }
        return value;
    }

}
