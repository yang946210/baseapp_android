package com.yang.base.util;

import android.app.Activity;
import android.view.View;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/***
 * @desc 反射工具类
 */
public class BaseReflectHelper {


    /***
     * 是否是接口
     * @param classZ  检测类的.class
     * @return 检测结果
     */
    public static boolean isInterface(Class<?> classZ) {
        return classZ.isInterface();
    }

    /***
     * 查找类的属性变量
     * @param classZ 查找数据的.class
     * @param fieldName 数据名
     * @return
     */
    public static Field findField(Class<?> classZ, String fieldName, Class lastClass) throws Exception {
        if (classZ == lastClass) {
            return classZ.getDeclaredField(fieldName);
        } else {
            try {
                return classZ.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                return findField(classZ.getSuperclass(), fieldName);
            }
        }
    }

    /***
     * 查找类的属性变量
     * @param classZ  查找数据的.class
     * @param fieldName 数据名
     * @return
     */
    public static Field findField(Class<?> classZ, String fieldName) throws Exception {
        return findField(classZ, fieldName, Object.class);
    }


    /***
     * 设置值
     * @param object 被修改属性
     * @param fieldName 数据名
     * @param value 修改后的值
     * @return 操作结果
     */
    public static <T> boolean setValue(T object, String fieldName, Object value) throws Exception {
        boolean find = false;
        Field field = findField(object.getClass(), fieldName);
        if (field == null) {
            return false;
        }
        try {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            field.set(object, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }


    /***
     * 设置值
     * @param obj 对象
     * @param field 字段
     * @param value 值
     * @throws Exception
     */
    public static void setValue(Object obj, Field field, Object value) throws Exception {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        field.set(obj, value);
    }


    public static Object getValue(Object obj, Field field) throws Exception {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        return field.get(obj);
    }

    /***
     * 获取注解
     * @param field 变量
     * @param annotationClass 注解类
     * @param <T>
     * @return
     */
    public static <T> T getAnnotation(Field field, Class<T> annotationClass) {
        Annotation v_ans[] = field.getDeclaredAnnotations();
        if (v_ans == null || v_ans.length == 0) {
            return null;
        }
        for (Annotation an : v_ans) {
            Class b = an.annotationType();
            String c = b.getName();
            String d = annotationClass.getName();
            if (c.equals(d)) {
                return (T) an;
            }
        }
        return null;
    }


    /***
     * 查找这个类的变量
     * @param v_list 存储变量容器
     * @param classZ 要查找的类
     */
    public static void getField(List<Field> v_list, Class<?> classZ) throws Exception {
        Field v_fs[] = classZ.getDeclaredFields();
        for (Field f : v_fs) {
            v_list.add(f);
        }
        if (classZ.getSuperclass() != Activity.class) {
            getField(v_list, classZ.getSuperclass());
        }
    }

    /***
     * 根据参数获取方法对象
     * @param classZ 类
     * @param methodName 方法名称
     * @param param 方法参数
     * @return
     */
    public static Method getMethod(Class<?> classZ, String methodName, Class<?>... param) throws Exception {
        Method d = null;
        try {
            d = classZ.getDeclaredMethod(methodName, param);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        if (d == null && classZ != null && classZ != View.class) {
            return getMethod(classZ.getSuperclass(), methodName, param);
        } else {
            return d;
        }
    }


    /****
     * 执行方法
     * @param object
     * @param method
     * @param params
     * @return
     * @throws Exception
     */
    public static Object invokeMethod(Object object, Method method, Object... params) throws Exception {
        if (!method.isAccessible()) {
            method.setAccessible(true);
        }
        return method.invoke(object, params);
    }

}
