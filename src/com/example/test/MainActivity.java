package com.example.test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
	FancyDrawable clockDrawable;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageView iv = (ImageView) findViewById(R.id.icon);
		
		//String defaultIconResource = "/system/auitheme/default/icons";
		String defaultIconResource = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator + "icons";
		 Log.i("way", "defaultIconResource = " + defaultIconResource);
		 //AssetManager assets = new AssetManager();
        AssetManager assets = createAssetManager();
        //assets.addAssetPath(defaultIconResource);
        addAssetPathWithAssets(assets, defaultIconResource);
        Resources res = new Resources(assets, getResources().getDisplayMetrics(),
        		getResources().getConfiguration());
        try {
        	String iconOrigin = ConstantHelper.ICON_RESOURCE_PACKAGE_NAME;
            int mMaxIconSize = (int) res.getDimension(res.getIdentifier(ConstantHelper.IC_CANVAS_MAX_ICON_SIZE, "dimen", iconOrigin));
            int mMaxShadowSize = (int) res.getDimension(res.getIdentifier(ConstantHelper.IC_CANVAS_MAX_SHADOW_SIZE, "dimen", iconOrigin));
            int mIconSize = (int) res.getDimension(res.getIdentifier(ConstantHelper.IC_CANVAS_ICON_SIZE, "dimen", iconOrigin));
            int mShadowSize = (int) res.getDimension(res.getIdentifier(ConstantHelper.IC_CANVAS_SHADOW_SIZE, "dimen", iconOrigin));
            Log.i("way", "mMaxIconSize = " + mMaxIconSize + ", mMaxShadowSize = " + mMaxShadowSize
            	 + ", mIconSize = " + mIconSize + ", mShadowSize = " + mShadowSize);
            
            
            
            if (mIconSize > mMaxIconSize || mIconSize <= 0) {
                mIconSize = mMaxIconSize;
            }
            if (mShadowSize > mMaxShadowSize || mShadowSize <= 0) {
                mShadowSize = mMaxShadowSize;
            }
            if (mShadowSize < mIconSize) {
                mShadowSize = mIconSize;
            }
        } catch (Exception e) {
        }
        
//        String icontheme = ConstantHelper.iconsIdentity + ConstantHelper.PACKAGE_NAME_SUFFIX;
//        int redirectXmlResId = res.getIdentifier(ConstantHelper.AUI_THEME_REDIRECTION_ITEM_FILE_NAME, "xml", ConstantHelper.ICON_RESOURCE_PACKAGE_NAME);
//        
//        HashMap<IconKey, String> iconKeys = parseIconKeysFromXml(res.getXml(redirectXmlResId), redirectXmlResId);
//        
//        String packageName = "com.aliyun.homeshell";
//        int resId = 0;
//        Set<Entry<IconKey, String>> keyEntries = iconKeys.entrySet();
//        for (Entry<IconKey, String> entry : keyEntries) {
//            if (packageName.equals(entry.getKey().packageName)) {
//                resId = res.getIdentifier(entry.getValue(), "drawable", ConstantHelper.ICON_RESOURCE_PACKAGE_NAME);
//                break;
//            }
//        }
//        Log.i("way", "resId = " + resId);
//
//        if (resId > 0) {
//           Bitmap iconBm = BitmapFactory.decodeResource(res, resId);
//            if (null != iconBm) {
//            	iv.setImageBitmap(iconBm);
//            }
//        }
//        FancyDrawable clockDrawable = new ClockDrawable(this);
        clockDrawable = new CalendarDrawable(this);
        clockDrawable.setIntrinsicSize(120, 120);
        iv.setImageDrawable(clockDrawable);
        iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
        
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(clockDrawable != null)
			clockDrawable.unRegisterListener();
	}

	public static AssetManager createAssetManager() {
		AssetManager assets = null;
		try {
			assets = (AssetManager) AssetManager.class.getConstructor(null).newInstance(null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		if (assets == null) {
			Log.e("ACA", "asset reflection error");
			return null;
		}
		return assets;
	}

	public static int addAssetPathWithAssets(Object assets, String path) {
		if (assets == null) {
			Log.e("ACA", "asset reflection error");
			return 0;
		}
		int cookie = 0;
		try {
			Object cookieObj = AssetManager.class.getMethod("addAssetPath", String.class).invoke(assets, path);
			cookie = Integer.valueOf(cookieObj.toString());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		return cookie;
	}

	public static int attachThemePath(AssetManager assets, String path) {
		if (assets == null) {
			Log.e("ACA", "asset reflection error");
			return 0;
		}
		int cookie = 0;
		try {
			Object cookieObj = AssetManager.class.getMethod("addAssetPath", String.class).invoke(assets, path);
			cookie = Integer.valueOf(cookieObj.toString());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		return cookie;
	}

	
    /**
     * parse config file of icon theme package and acquire <{@link IconKey}, icon_res_name> map. 
     * @return
     */
    private HashMap<IconKey, String> parseIconKeysFromXml(XmlPullParser parser, int redirectXmlResId) {
        HashMap<IconKey, String> iconKeys = new HashMap<IconKey, String>();
        try {
            int type;
            while ((type = parser.next()) != XmlPullParser.START_TAG
                    && type != XmlPullParser.END_DOCUMENT) {
            }

			String tagName = parser.getName();
			if (parser.getName().equals(ConstantHelper.AUI_THEME_REDIRECTION_ITEM_TAG)) {
				final int innerDepth = parser.getDepth();
				String attriName;
				String resName;
				String xmlTag;
				String iconInfo;
				while ((type = parser.next()) != XmlPullParser.END_DOCUMENT
						&& (type != XmlPullParser.END_TAG || parser.getDepth() > innerDepth)) {
					if (type == XmlPullParser.END_TAG || type == XmlPullParser.TEXT) {
						continue;
					}
					attriName = parser.getAttributeName(0);
					xmlTag = parser.getName();
					if (!ConstantHelper.FILE_ATTRIBUTE_TAG.equals(attriName) || !ConstantHelper.FILE_DRAWABLE_TAG.equals(xmlTag))
						continue;
					resName = parser.getAttributeValue(null, attriName);
					iconInfo = parser.nextText();
					if (TextUtils.isEmpty(iconInfo)) {
						Log.d("way",
								"Missing value on <item> tag at "
										+ getResourceLabel(redirectXmlResId) + " "
										+ parser.getPositionDescription());
						continue;
					}
					iconInfo = iconInfo.trim();
					//TODO package_class中加的特殊字符间隔符取什么？
					final String separator = ";";
					//TODO =======================
					String[] splits = iconInfo.split(separator);
					if (splits.length > 2)
						continue;
					String packageName = splits[0];
					String className = (splits.length > 1 ? splits[1] : null);
					iconKeys.put(new IconKey(packageName, className, 0), resName);
				}
			} else {
				Log.d("way",
						"Unknown root element: " + tagName + " at "
								+ getResourceLabel(redirectXmlResId) + " "
								+ parser.getPositionDescription());
			}
		} catch (XmlPullParserException e) {
			iconKeys.clear();
			iconKeys = null;
			Log.d("way", "Malformed theme redirection meta at "
					+ getResourceLabel(redirectXmlResId));
		} catch (IOException e) {
			iconKeys.clear();
			iconKeys = null;
			Log.d("way", "Unknown error reading redirection meta at "
					+ getResourceLabel(redirectXmlResId));
		}
		
		return iconKeys;
	}
	
	private static String getResourceLabel(int resID) {
		return "resource #0x" + Integer.toHexString(resID);
	}
	
	private class IconKey {
		String packageName;
		String className;
		int icon;
		public IconKey(String packageName, String className, int icon) {
			this.packageName = packageName;
			this.className = className;
			this.icon = icon;
		}
		@Override
		public String toString() {
			return "packageName="+packageName+",className="+className+",icon="+icon;
		}
        @Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			//result = prime * result + getOuterType().hashCode();
			result = prime * result
					+ ((packageName == null) ? 0 : packageName.hashCode());
//			result = prime * result + icon;
//			result = prime * result
//					+ ((className == null) ? 0 : className.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			IconKey other = (IconKey) obj;
			//if (!getOuterType().equals(other.getOuterType()))
			//	return false;
			if (packageName == null) {
				if (other.packageName != null)
					return false;
			} else if (!packageName.equals(other.packageName))
				return false;
			if (icon != other.icon || 0 == icon) {
				if (null == className) {
					if (null != other.className)
						return false;
				}
				else if (!className.equals(other.className))
					return false;
			}
//			if (icon != other.icon || 0 == icon) {
//				boolean classUnequal = false;
//				if (className == null) {
//					if (other.className != null)
//						classUnequal = true;
//				} else if (null == other.className
//						|| (!className.equals(other.className)
//								&& !className.endsWith(other.className)
//								&& !other.className.endsWith(className)))
//					classUnequal = true;
//				if (classUnequal) {
//					Log.d(TAG, className + " is unequal to " + other.className);
//					return false;
//				}
//			}
			return true;
		}
		
	}
}
