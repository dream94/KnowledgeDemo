package com.example.cc.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

/**
 * Created by cc on 2018/10/18.
 */

public class AppUtils {

    public static void testDemo(Context context) {
        Log.e("dream", "alipay exist:" + isAppInstalled(context, "com.eg.android.AlipayGphone"));

        String gamePkg = "com.netease.gl";
        String url = "http://www.baidu.com";
        openApp(context, gamePkg, url);
    }

    // 判断app是否安装
    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            if (TextUtils.isEmpty(packageName)) {
                return false;
            }
            return context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES) != null;
        } catch (PackageManager.NameNotFoundException localNameNotFoundException) {
            return false;
        }
    }


    // 根据包名打开app
    public static void openApp(Context context, String appPkg, String url) {
        PackageInfo packageInfo = null;
        if (!TextUtils.isEmpty(appPkg)) {
            try {
                packageInfo = context.getPackageManager().getPackageInfo(appPkg, 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if (packageInfo != null) {
                Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
                resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                resolveIntent.setPackage(packageInfo.packageName);
                // 通过getPackageManager()的queryIntentActivities方法遍历
                List<ResolveInfo> resolveinfoList = context.getPackageManager().queryIntentActivities(resolveIntent, 0);
                ResolveInfo resolveinfo = resolveinfoList.iterator().next();
                if (resolveinfo != null) {
                    // packagename = 参数packname
                    String packageName = resolveinfo.activityInfo.packageName;
                    // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
                    String className = resolveinfo.activityInfo.name;
                    // LAUNCHER Intent
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    // 设置ComponentName参数1:packagename参数2:MainActivity路径
                    ComponentName cn = new ComponentName(packageName, className);
                    intent.setComponent(cn);
                    context.startActivity(intent);
                    Log.e("dream", "startActivity");
                }
            } else {
                if (TextUtils.isEmpty(url)) {
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(url));
                context.startActivity(intent);
                Log.e("dream", "start browsable");
            }
        }
    }
}
