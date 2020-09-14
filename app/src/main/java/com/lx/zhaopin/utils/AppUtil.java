package com.lx.zhaopin.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class AppUtil {

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public static float sp2px(Context context, int spValue) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics());
	}

	public static int getWidth(Context context) {
		DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
		return dm.widthPixels;
	}

	public static int getHeight(Context context) {
		DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
		return dm.heightPixels;
	}

	public static void showSoftInput(Context aContext, View view) {
		InputMethodManager im = ((InputMethodManager) aContext.getSystemService(Context.INPUT_METHOD_SERVICE));
		im.showSoftInput(view, 0);
	}

	public static void hideSoftInput(Context aContext, View view) {
		InputMethodManager im = ((InputMethodManager) aContext.getSystemService(Context.INPUT_METHOD_SERVICE));
		im.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	public static boolean isOpenInputMethod(Context aContext) {
		try {
			InputMethodManager imm = (InputMethodManager) aContext.getSystemService(Context.INPUT_METHOD_SERVICE);
			return imm.isActive();
		} catch (Exception e) {
			return false;
		}
	}

	public static final boolean isApkInstalled(Context context, String packageName) {
		try {
			context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
			return true;
		} catch (NameNotFoundException e) {
			return false;
		}
	}

	public static String getVersion(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			String version = info.versionName;
			return version;
		} catch (Exception e) {
			e.printStackTrace();

			return "未找到版本号" ;
		}
	}


}
