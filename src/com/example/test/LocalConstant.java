/**
 * @(#)UnifiedConstant.java    v1.0 2013-1-8
 * 
 * Copyright (c) 2012-2012  yunos, Inc. 
 * 2 zijinghua Road, HangZhou, C.N 
 * All rights reserved. 
 */
package com.example.test;

import java.io.File;

/**
 * 
 */
public class LocalConstant {

    
    /*file path constant*/    
    private static String INTERNAL_RESOURCE_BASE_PATH = "";
    private static String DEFAULT_THEME_PATH = INTERNAL_RESOURCE_BASE_PATH + "default" + File.separator;
    protected static String DEFAULT_ICON_RESOURCE = DEFAULT_THEME_PATH + ConstantHelper.iconsIdentity;
    protected static String DEFAULT_TRAFFIC_PANEL = DEFAULT_THEME_PATH + ConstantHelper.trafficPanelIdentity;
    
    protected static String INTERNAL_THEME_PATH = INTERNAL_RESOURCE_BASE_PATH + "theme" + File.separator;
    
  
    
    private static void updatePathConstant() {
        INTERNAL_THEME_PATH = INTERNAL_RESOURCE_BASE_PATH + "theme" + File.separator;
        
        DEFAULT_THEME_PATH = INTERNAL_RESOURCE_BASE_PATH + "default" + File.separator;
        DEFAULT_ICON_RESOURCE = DEFAULT_THEME_PATH + ConstantHelper.iconsIdentity;
        DEFAULT_TRAFFIC_PANEL = DEFAULT_THEME_PATH + ConstantHelper.trafficPanelIdentity;
    }
}
