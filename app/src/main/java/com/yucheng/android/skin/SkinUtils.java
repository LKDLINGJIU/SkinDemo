package com.yucheng.android.skin;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by lingjiu on 2018/11/12.
 */

public class SkinUtils {

    static void copyFilesFromAssets(Context context,String fileName , String savePath) {
        try {
            InputStream is = context.getAssets().open(fileName);
            FileOutputStream fos = new FileOutputStream(new File(savePath));
            byte[] buffer = new byte[1024];
            int byteCount = 0;
            while ((byteCount = is.read(buffer)) != -1) {// 循环从输入流读取
                // buffer字节
                fos.write(buffer, 0, byteCount);// 将读取的输入流写入到输出流
            }
            fos.flush();// 刷新缓冲区
            is.close();
            fos.close();
            Toast.makeText(context, "写入ok", Toast.LENGTH_LONG).show();
        } catch (Exception e) { // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}