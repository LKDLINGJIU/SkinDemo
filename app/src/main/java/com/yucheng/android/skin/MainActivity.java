package com.yucheng.android.skin;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("PrivateApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View btn = findViewById(R.id.btn);

        int id = getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusHeight = getResources().getDimensionPixelOffset(id);
        if (statusHeight > 0) {
            Log.i("lj", "statusHeight=" + statusHeight);
        }

        String apkPath = new File(getCacheDir(), "orange.apk").getPath();
        SkinUtils.copyFilesFromAssets(this, "orange.apk",
                apkPath);

        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = AssetManager.class.getDeclaredMethod("addAssetPath", new Class<?>[]{String.class});
            addAssetPath.invoke(assetManager, apkPath);
            Resources extraResource = new Resources(assetManager, getResources().getDisplayMetrics(),
                    getResources().getConfiguration());
            String extraPackageName = getPackageManager().getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES).packageName;

            int colorPrimaryId = extraResource.getIdentifier("colorPrimary", "color", extraPackageName);
            int color = extraResource.getColor(colorPrimaryId);
            btn.setBackgroundColor(color);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
