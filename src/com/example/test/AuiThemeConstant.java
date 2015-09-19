/**
 * @(#)AuiThemeConstant.java    v1.0 2012-12-31
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
public class AuiThemeConstant {
    
    /*file path constant*/
    private static final String AUI_DEFAULT_INNER_PATH = "/system/auitheme/";
    public static final String DEFAULT_THEME_PATH = AUI_DEFAULT_INNER_PATH + "default" + File.separator;
    public static final String PRESET_THEME_PATH = AUI_DEFAULT_INNER_PATH + "inner_themes" + File.separator;
    public static final String DEFAULT_ICON_RESOURCE = DEFAULT_THEME_PATH + ConstantHelper.iconsIdentity;
    public static final String DEFAULT_TRAFFIC_PANEL = DEFAULT_THEME_PATH + ConstantHelper.trafficPanelIdentity;
    public static final String DEFAULT_HOMESHELL_RES = DEFAULT_THEME_PATH + ConstantHelper.homeshellResIdentity;
    public static final String DEFAULT_FANCY_ICONS = DEFAULT_THEME_PATH + ConstantHelper.fancyIconIdentity;
    public static final String DEFAULT_LOCK_STYLE = DEFAULT_THEME_PATH + ConstantHelper.lockstyleIdentity;
    
    public static final String AUI_INTERNAL_THEME_PATH = "/data/system/auitheme/";
    public static final String INTERNAL_THEME_PATH = AUI_INTERNAL_THEME_PATH + "theme" + File.separator;
    
    public static final String AUI_FONT_PATH = "/data/system/auitheme/fonts/";
    public static final String PACKAGE_ATTR = "package";
}
