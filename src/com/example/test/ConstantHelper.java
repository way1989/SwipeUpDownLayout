/**
 * @(#)CommonConstant.java    v1.0 2013-1-8
 * 
 * Copyright (c) 2012-2012  yunos, Inc. 
 * 2 zijinghua Road, HangZhou, C.N 
 * All rights reserved. 
 */
package com.example.test;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;


/**
 * Be careful when you try to change these constant, because they may used every where.
 */
public class ConstantHelper {
    public static final int FLAG_BACKUP_WALLPAPER = 0;
    public static final int FLAG_DONT_BACKUP_WALLPAPER = 1;
    
    private static boolean isYunos = SystemUtils.isYunOS();
    public static final String AUI_THEME_FILE_ENCODE = "UTF-8";
    public static final String AUI_THEME_REDIRECTION_ITEM_FILE = "items.xml";
    public static final String CURRENT_THEME_RECORD_FILE = "auitheme.xml";
    public static final String THEME_PACKAGE_CONFIG_FILE = "theme-config.xml";
    public static final String AUI_THEME_FILENAME_EXTENSION = ".zip";
    public static final String AUI_THEME_PACKAGE_PREFIX = "com.yunos.theme.p.";
    
    /*custom icon constant*/
    public static final String ICON_RESOURCE_PACKAGE_NAME = "iconOrigin";
    public static final int ICON_TYPE_APP = 1;
    public static final int ICON_TYPE_CLOUDAPP = 2;
    public static final int ICON_TYPE_BROSWER_SHORTCUT = 3;
    public static final int ICON_TYPE_FOLDER = 4;
    public static final int ICON_TYPE_APP_TEMPORARY = 5;

    // Required resource
    public static final String IC_CANVAS_MAX_ICON_SIZE = "max_icon_size";
    public static final String IC_CANVAS_MAX_SHADOW_SIZE = "max_shadow_size";
    public static final String IC_CANVAS_ICON_SIZE = "icon-size";
    public static final String IC_CANVAS_SHADOW_SIZE = "shadow-size";
    public static final String IC_DEFAULT_APP = "aui_ic_default_app";
    public static final String IC_DEFAULT_WEB = "aui_ic_default_web";
    public static final String IC_DEFAULT_CLOUDAPP = "aui_ic_default_cloudapp";
    public static final String IC_SHADOW = "aui_ic_shadow";
    
    // web icon
    public static final String IC_ALPHA_WEB = "aui_ic_alpha_web";
    public static final String IC_MASK_WEB = "aui_ic_mask_web";
    public static final String IC_BG_WEB = "aui_ic_bg_web";
    // cloud app icon
    public static final String IC_ALPHA_CLOUDAPP = "aui_ic_alpha_cloudapp";
    public static final String IC_MASK_CLOUDAPP = "aui_ic_mask_cloudapp";
    public static final String IC_BG_CLOUDAPP = "aui_ic_bg_cloudapp";
    // group icon
    public static final String IC_MASK_GROUP = "aui_ic_mask_group";
    public static final String IC_BG_GROUP = "aui_ic_bg_group";
    // custom app icon
    public static final String IC_ALPHA_APP_CUSTOM = "aui_ic_alpha_app_custom";
    public static final String IC_MASK_APP_CUSTOM = "aui_ic_mask_app_custom";
    public static final String IC_BG_APP_CUSTOM = "aui_ic_bg_app_custom";
    // unified app icon
    public static final String IC_ALPHA_APP_UNIFIED = "aui_ic_alpha_app_unified";
    public static final String IC_MASK_APP_UNIFIED = "aui_ic_mask_app_unified";
    public static final String IC_BG_APP_UNIFIED = "aui_ic_bg_app_unified";

    
    /*config file constant*/
    public static final String PACKAGE_NAME_SUFFIX = "theme";
    public static final String CONFIG_THEME_FILE_TAG = "YUNOS-THEME";
    public static final String CONFIG_AUTHOR_INFO_TAG = "author-info";
    public static final String CONFIG_NAME_TAG = "name";
    public static final String CONFIG_ENGLISH_NAME_TAG = "english_name";
    public static final String CONFIG_INTRO_TAG = "intro";
    public static final String CONFIG_PACKAGE_TAG = "package";
    public static final String CONFIG_AUTHOR_TAG = "author";
    public static final String CONFIG_DESIGNER_TAG = "designer";
    public static final String CONFIG_VERSION_TAG = "version";
    public static final String CONFIG_OS_VERSION_TAG = "osversion";
    public static final String CONFIG_THRID_LOCK_TAG = "lockstyle_package"; //for third party lockstyle
    public static final String AUI_THEME_REDIRECTION_ITEM_FILE_NAME = "items";
    public static final String AUI_THEME_REDIRECTION_ITEM_TAG = "resource-items";
    /*YUNOS BEGIN*/
    //yunos theme will inherit it for Global Themes
    //##date:2014-05-25 ##author:chusheng.xcs@aliyun-inc.com
    public static final String CONFIG_ITEM_RES_TAG = "res_item";
    /*YUNOS END*/
    public static final String CONFIG_ITEM_TAG = "item";
    public static final String CONFIG_ITEM_PARENT_TAG = "items";
    public static final String FILE_ATTRIBUTE_TAG = "name";
    public static final String FILE_DRAWABLE_TAG = "drawable";
    public static final String FILE_COLOR_TAG = "color";
    public static final String FILE_DIMENSION_TAG = "dimen";
    public static final String FILE_STRING_TAG = "string";

    /*item identity constant*/
    public static final String trafficPanelIdentity = "com.yunos.trafficpanel";
    public static final String frameworkResIdentity = "framework-res";
    public static final String buttonStyleIdentity = "buttonstyle";
    public static final String iconsIdentity = "icons";
    public static final String wallpaperIdentity = "wallpaper";
    public static final String lockScreenWallpaperIdentity = "lockwallpaper";
    public static final String ringtoneIdentity = "ringtone";
    public static final String notificationIdentity = "notification";
    public static final String fontIdentity = "font";
    //add for lockScreen Style
    public static final String lockstyleIdentity = "lockstyle";
    //end
    public static final String fancyIconIdentity = "fancyicons";
    public static final String homeshellResIdentity = "com.aliyun.homeshell";
    public static final String alarmIdentity = "alarm";
    public static final String gadgetsIdentity = "gadgets";
    public static final String globelResIdentity = "globel_res";
    /* boot file*/
    public static final String bootaudioIdentity = "bootaudio";
    
    private static final String[] mediaIdentities = new String[]{
        wallpaperIdentity, lockScreenWallpaperIdentity,
        ringtoneIdentity, notificationIdentity, alarmIdentity,
        bootaudioIdentity};
    
    /* theme pakcage info*/
    public static final String CACHE_FOLDER = ".cache" + File.separator;
    public static final String WALLPAPER_FOLDER = "wallpapers" + File.separator;
    public static final String RING_FOLDER = "ringtones" + File.separator;
    public static final String FONT_FOLDER = "font" + File.separator;
    public static final String PREVIEW_FOLDER = "previews" + File.separator;
    /*YUNOS BEGIN*/
    //yunos theme will inherit it for Global Themes
    //##date:2014-05-25 ##author:chusheng.xcs@aliyun-inc.com
    public static final String RES_FOLDER = "res" + File.separator;
    /*YUNOS END*/
    public static final String THEME_PREVIEW_COVER = "preview_theme";  //"preview_wallpaper"
    public static final String LOCKSTYLE_PREVIEW_COVER = "preview_lockstyle";  //"preview_wallpaper"
    public static final String FONT_PREVIEW_COVER = "preview_font";  //"preview font"
    public static final String ICONS_PREVIEW_COVER = "preview_icons";  //"preview font"
    public static final String THEME_PREVIEW_PREFIX = PREVIEW_FOLDER + THEME_PREVIEW_COVER;
    public static final String LOCKSTYLE_PREVIEW_PREFIX = PREVIEW_FOLDER + LOCKSTYLE_PREVIEW_COVER;
    public static final String PREVIEW_PREFIX = "preview_";
    public static final String THEMES_FOLDER =  "auitheme" + File.separator;
    private static final String THEMES_DIRECTOR_NAME = "themes" + File.separator;


    private static final String BACKUP_THEME_NAME = "backup" + AUI_THEME_FILENAME_EXTENSION;

    /* database constant */
    public static final String AUI_THEME_AUTHORITY = "com.yunos.theme.themeconfigprovider";
    public static final String AUI_THEME_DATABASE_NAME = "themeconfig";
    public static final Uri THEME_CONFIG_URI =
            Uri.parse("content://" + AUI_THEME_AUTHORITY + "/" + AUI_THEME_DATABASE_NAME);
     public static final String THEME_FONT_FILE_NAME = "auitheme_font.ttf";
    public static final String THUMBS_DB = "Thumbs.db";
   
}
