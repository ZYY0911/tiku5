package com.example.tiku5.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * Create by 张瀛煜 on 2019-12-26
 */
public class ShowDialog {
    public static void Show(String msg, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(msg);
        builder.setPositiveButton("确定", null);
        builder.show();
    }


}
