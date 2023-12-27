package com.yang.ktbase.extorutil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType


/**
 * 构建泛型viewBind(activity)
 */
fun <B : ViewBinding> AppCompatActivity.inflateBindingWithGeneric(layoutInflater: LayoutInflater): B =
    withGenericBindingClass(this) { clazz ->
        clazz.getMethod("inflate", LayoutInflater::class.java).invoke(null, layoutInflater) as B
    }

/**
 * 构建泛型viewBind(fragment)
 */
fun <B : ViewBinding> Fragment.inflateBindingWithGeneric(layoutInflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean): B =
    withGenericBindingClass(this) { clazz ->
        clazz.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
            .invoke(null, layoutInflater, parent, attachToParent) as B
    }

/**
 * 根据泛型获取实列类
 */
private fun <B : ViewBinding> withGenericBindingClass(genericOwner: Any, block: (Class<B>) -> B): B {
    var genericSuperclass = genericOwner.javaClass.genericSuperclass
    var superclass = genericOwner.javaClass.superclass
    while (superclass != null) {
        if (genericSuperclass is ParameterizedType) {
            genericSuperclass.actualTypeArguments.forEach {
                try {
                    return block.invoke(it as Class<B>)
                } catch (_: NoSuchMethodException) {
                } catch (_: ClassCastException) {
                } catch (e: InvocationTargetException) {
                    var tagException: Throwable? = e
                    while (tagException is InvocationTargetException) {
                        tagException = e.cause
                    }
                    throw tagException
                        ?: IllegalArgumentException("ViewBinding generic was found, but creation failed.")
                }
            }
        }
        genericSuperclass = superclass.genericSuperclass
        superclass = superclass.superclass
    }
    throw IllegalArgumentException("There is no generic of ViewBinding.")
}