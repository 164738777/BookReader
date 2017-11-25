package com.justwayward.reader.utils;

import android.app.Activity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener;

/**
 * 作者:  lbqiang on 2017/11/25 14:30
 * 邮箱:  anworkmail_q@126.com
 * 作用:  Android 6.0 动态权限工具类
 */

public class RuntimePermissionUtil {

    public static void checkPermission(Activity activity, String[] permissions, final OnRequestPermissionListener listener) {
        Dexter.withActivity(activity)
                .withPermissions(permissions)
                .withListener(new BaseMultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (listener != null && report.areAllPermissionsGranted()) {
                            listener.onRequestSuccess();
                        }
                    }
                })
                .withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        if (listener != null) {
                            listener.onRequestError();
                        }
                    }
                })
                .check();
    }

    public interface OnRequestPermissionListener {
        void onRequestSuccess();

        void onRequestError();
    }
}
