package com.yang.base.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ScrollView;


import com.yang.base.BaseSdk;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/***
 * @desc 图片处理工具类
 * @time 2020-07-01
 * @author hyc
 */
public class BaseImageHelper {

    /**
     * 打开图片选择界面
     * @param context     上下文
     * @param requestCode Activity请求code
     */
    public static void showImageChooser(Activity context, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            context.startActivityForResult(Intent.createChooser(intent, "选择文件"), requestCode);
        } catch (Exception ex) {
            BaseToastHelper.showToast("未安装文件管理应用");
        }
    }

    /**
     * 调用系统图片裁剪(可设置裁剪框比例)
     * @param context     　上下文
     * @param inputUri    原图
     * @param outUri      结果图
     * @param aspectX     裁剪框宽度比
     * @param aspectY     裁剪框高度比
     * @param outputX     　　输出宽度
     * @param outputY     　　输出高度
     * @param requestCode 　activity请求code
     */
    public static void cropImageUri(Activity context, Uri inputUri, Uri outUri, int aspectX, int aspectY, int outputX, int outputY, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(inputUri, "image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra("crop", "true");
        if ("HUAWEI".equals(Build.MANUFACTURER.toUpperCase())) {
            intent.putExtra("aspectX", 9998);
            intent.putExtra("aspectY", 9999 * aspectY / aspectX);
        } else {
            intent.putExtra("aspectX", aspectX);
            intent.putExtra("aspectY", aspectY);
        }
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        context.startActivityForResult(intent, requestCode);
    }

    /***
     * 添加水印到view 背景
     * @param view view
     * @param text1 文字1
     * @param text2 文字2
     * @param color 背景色
     */
    public static void addWaterText2ViewBackground(View view, String text1, String text2, int color) {
        try {
            Bitmap bitmap = Bitmap.createBitmap(360, 260, Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            canvas.drawColor(color);
            Paint paint = new Paint();
            paint.setColor(Color.GRAY);
            paint.setAlpha(60);
            paint.setAntiAlias(true);
            paint.setTextAlign(Paint.Align.LEFT);
            paint.setTextSize(50);
            Path path = new Path();
            path.moveTo(40, 150);
            path.lineTo(120 * 2, 0);
            canvas.drawTextOnPath(text1, path, 0, -30, paint);
            canvas.drawTextOnPath(text2, path, 0, 30, paint);
            canvas.save();
            canvas.restore();
            BitmapDrawable drawable = new BitmapDrawable(view.getResources(), bitmap);
            drawable.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
            drawable.setDither(true);
            view.setBackground(drawable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 添加水印到view 背景
     * @param view view
     * @param text1 文字1
     * @param text2 文字2
     * @param drawableId 资源id
     */
    public static void addWaterTextBitmap2ViewBackground(Context context, View view, String text1, String text2, int drawableId) {
        try {
            int displayWidth = BaseDeviceHelper.getDisplayWidth(context);
            Bitmap bitmap = Bitmap.createBitmap(displayWidth, 260, Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), drawableId);
            canvas.drawBitmap(bitmap1, 0, 0, null);
            Paint paint = new Paint();
            paint.setColor(Color.GRAY);
            paint.setAlpha(60);
            paint.setAntiAlias(true);
            paint.setTextAlign(Paint.Align.LEFT);
            paint.setTextSize(50);
            Path path = new Path();
            int index = 350;
            for (int i = 0; i < 3; i++) {
                path.moveTo(40 + index * i, 150);
                path.lineTo(120 * 2 + index * i, 0);
                canvas.drawTextOnPath(text1, path, 0, -30, paint);
                canvas.drawTextOnPath(text2, path, 0, 30, paint);
                path.reset();
            }
            canvas.save();
            canvas.restore();
            BitmapDrawable drawable = new BitmapDrawable(view.getResources(), bitmap);
            drawable.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
            drawable.setDither(true);
            view.setBackground(drawable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 获取图片
     * @param path 图片路径
     * @return
     */
    public static Bitmap getBitmap(String path) {
        InputStream inputStream = null;
        try {
            if (path.startsWith("assets://")) {
                return BitmapFactory.decodeStream(inputStream = BaseSdk.getInstance().getContext().getAssets().open(path));
            } else if (path.startsWith("file://")) {
                path = path.substring(7);
            }
            return BitmapFactory.decodeStream(inputStream = new FileInputStream(path));
        } catch (Exception e) {
            e.printStackTrace();
            System.gc();
            return null;
        } finally {
            BaseCommHelper.closeStream(inputStream);
        }
    }

    /***
     * 保存图片
     * @param bitmap 图片
     * @param picPath 保存路径
     * @param format 保存格式
     * @return
     */
    public static boolean savePic(Bitmap bitmap, String picPath, Bitmap.CompressFormat format) {
        return savePic(bitmap, picPath, format, 100);
    }

    /***
     * 保存图片
     * @param bitmap 图片
     * @param picPath 保存路径
     * @param format 保存格式
     * @param quanlity 保存质量 0-100
     * @return
     */
    public static boolean savePic(Bitmap bitmap, String picPath, Bitmap.CompressFormat format, int quanlity) {
        if (bitmap == null) {
            return false;
        }
        File myCaptureFile = new File(picPath);
        if (!myCaptureFile.getParentFile().exists()) {
            myCaptureFile.getParentFile().mkdirs();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myCaptureFile);
            bitmap.compress(format, quanlity, fos);
            fos.flush();
            return true;
        } catch (Throwable e) {
            return false;
        } finally {
            BaseCommHelper.closeStream(fos);
        }
    }

    /**
     * 将图片按照某个角度进行旋转
     * @param bm     需要旋转的图片
     * @param degree 旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;
        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (Throwable e) {
            e.printStackTrace();
            return bm;
        }
        return returnBm;
    }

    /***
     * 将图片转换为base64数据
     * @param filePath 图片路径
     * @return
     */
    public static String imgToBase64(String filePath) {
        ByteArrayOutputStream out = null;
        Bitmap bitMap = null;
        try {
            bitMap = BitmapFactory.decodeFile(filePath);
            if (bitMap == null) {
                return null;
            }
            out = new ByteArrayOutputStream();
            bitMap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            byte[] imgBytes = out.toByteArray();
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);
        } catch (Throwable e) {
            e.printStackTrace();
            System.gc();
            return null;
        } finally {
            if (bitMap != null) {
                bitMap.recycle();
            }
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /***
     * 将图片转换为base64数据
     * @param filePath 图片路径
     * @return
     */
    public static String pngImgToBase64(String filePath) {
        ByteArrayOutputStream out = null;
        Bitmap bitMap = null;
        try {
            bitMap = BitmapFactory.decodeFile(filePath);
            if (bitMap == null) {
                return null;
            }
            out = new ByteArrayOutputStream();
            bitMap.compress(Bitmap.CompressFormat.PNG, 100, out);
            byte[] imgBytes = out.toByteArray();
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);
        } catch (Throwable e) {
            e.printStackTrace();
            System.gc();
            return null;
        } finally {
            if (bitMap != null) {
                bitMap.recycle();
            }
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
            }
        }
    }

    /***
     * 生成圆角图片
     * @param bitmap 图片
     * @param pixels 圆角半径
     * @return
     */
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;

    }


    /***
     * 将view转换为图片
     * @param v view
     * @param hasBackground  是否有背景
     * @return
     */
    public static Bitmap getBitmapFromView(View v, boolean hasBackground) {
        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);
        int color = 0;
        if (!hasBackground) {
            color = v.getDrawingCacheBackgroundColor();
            v.setDrawingCacheBackgroundColor(0);
            if (color != 0) {
                v.destroyDrawingCache();
            }
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        if (!hasBackground) {
            v.setDrawingCacheBackgroundColor(color);
        }
        if (cacheBitmap == null) {
        }
        return cacheBitmap;
    }


    /**
     * 获取scrollview的截屏
     */
    public static Bitmap getScrollViewScreenShot(ScrollView scrollView) {
        int h = 0;
        Bitmap bitmap = null;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
        }
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h, Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        return bitmap;
    }

    /***
     * Bitmap縮放
     * @param oldBmp 源图片
     * @param newWidth 输出宽度
     * @param newHeight 输出高度
     * @return
     */
    public static Bitmap zoomBitMap(Bitmap oldBmp, int newWidth, int newHeight) {
        try {
            int width = oldBmp.getWidth();
            int height = oldBmp.getHeight();
            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            Bitmap resizedBitmap = Bitmap.createBitmap(oldBmp, 0, 0, width, height, matrix, true);
            oldBmp.recycle();
            return resizedBitmap;
        } finally {
            System.gc();
        }
    }

    /**
     * 缩放
     * @param drawable 源图片
     * @param scaleX   X方向缩放比例
     * @param scaleY   Y方向缩放比例
     * @return
     */
    public static Drawable zoomDrawable(Drawable drawable, float scaleX, float scaleY) {
        try {
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            Bitmap oldbmp = drawableToBitmap(drawable);
            Matrix matrix = new Matrix();
            matrix.postScale(scaleX, scaleY);
            Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height, matrix, true);
            oldbmp.recycle();
            return new BitmapDrawable(newbmp);
        } finally {
            drawable = null;
            System.gc();
        }
    }

    /**
     * 缩放
     * @param oldbmp 源图片
     * @param scaleX X方向缩放比例
     * @param scaleY Y方向缩放比例
     * @return
     */
    public static Bitmap zoomBitmap(Bitmap oldbmp, float scaleX, float scaleY) {
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX, scaleY);
        int width = oldbmp.getWidth();
        int height = oldbmp.getHeight();
        return Bitmap.createBitmap(oldbmp, 0, 0, width, height, matrix, true);
    }

    /**
     * Drawable縮放
     * @param drawable 图片
     * @param w        输出宽度
     * @param h        输出高度
     * @return
     */
    public static Drawable zoomDrawable(Drawable drawable, int w, int h) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        // drawable转换成bitmap
        Bitmap oldbmp = drawableToBitmap(drawable);
        // 创建操作图片用的Matrix对象
        Matrix matrix = new Matrix();
        // 计算缩放比例
        float sx = ((float) w / width);
        float sy = ((float) h / height);
        // 设置缩放比例
        matrix.postScale(sx, sy);
        // 建立新的bitmap，其内容是对原bitmap的缩放后的图
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height, matrix, true);
        oldbmp.recycle();
        return new BitmapDrawable(newbmp);
    }

    /***
     * 获取倒影图片
     * @param bitmap 图片
     * @return
     */
    public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
        final int reflectionGap = 4;
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, h / 2, w, h / 2, matrix, false);

        Bitmap bitmapWithReflection = Bitmap.createBitmap(w, (h + h / 2), Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmapWithReflection);
        canvas.drawBitmap(bitmap, 0, 0, null);
        Paint deafalutPaint = new Paint();
        canvas.drawRect(0, h, w, h + reflectionGap, deafalutPaint);

        canvas.drawBitmap(reflectionImage, 0, h + reflectionGap, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0, bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff, 0x00ffffff, TileMode.CLAMP);
        paint.setShader(shader);
        // Set the Transfer mode to be porter duff and destination in
        paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        // Draw a rectangle using the paint with our linear gradient
        canvas.drawRect(0, h, w, bitmapWithReflection.getHeight() + reflectionGap, paint);

        return bitmapWithReflection;
    }

    /**
     * 获取圆角图片
     * @param bitmap  图片
     * @param roundPx 圆角半径
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(w, h, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, w, h);
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * 將drawable转换为Bitmap
     * @param drawable 图片
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888 : Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }

}
