package com.yang.base.util;

import android.content.Context;

import com.yang.base.BaseSdk;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/***
 * @desc 文件相关
 * @author yangguoq
 */

public class BaseFileHelper {


    /***
     * 从asset中读取文件
     * @param context 上下文
     * @param fileName 文件全名
     * @return
     */
    public static byte[] readFileFromAsset(Context context, String fileName) {
        InputStream input;
        try {
            input = BaseSdk.getInstance().getContext().getAssets().open(fileName);
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();
            return buffer;
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    /***
     * 写文件
     * @param fileFullPath 文件全路径
     * @param data 写入文件数据
     * @return 写文件结果
     */
    public static boolean writeData(String fileFullPath, byte[] data) {
        FileOutputStream outputStream = null;
        try {
            File file = new File(fileFullPath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            outputStream = new FileOutputStream(file);
            outputStream.write(data);
            outputStream.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (outputStream != null) {
                BaseCommHelper.closeStream(outputStream);
            }
        }
    }

    /***
     * 读取文件
     * @param path 文件路径
     * @return
     */
    public static byte[] readFile(String path) {
        File f = new File(path);
        if (!f.exists() || f.isDirectory()) {
            return null;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int len = 0;
        byte[] buffer = new byte[1024];
        FileInputStream is = null;
        try {
            is = new FileInputStream(f);
            while ((len = is.read(buffer)) > 0) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            BaseCommHelper.closeStream(is);
            BaseCommHelper.closeStream(bos);
        }
    }

    /**
     * 获取文件大小
     * @param path 文件路径
     * @return
     */
    public static long getFileSize(String path) {
        File v_f = new File(path);
        if (!v_f.exists() || v_f.isDirectory()) {
            return 0;
        } else {
            return v_f.length();
        }
    }

}
