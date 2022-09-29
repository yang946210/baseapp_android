#include <jni.h>
#include <string>


extern "C"
JNIEXPORT jstring JNICALL

/**
 *
 * @param env
 * @param instance java stringFromJNI方法对应的对象
 * @return
 */
Java_com_yang_natives_NativeBean_stringFromJNI(JNIEnv *env, jobject instance) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_yang_natives_NativeBean_intFromJNI(JNIEnv *env, jobject, jstring name, jint age,
                                            jfloat point) {

    // TODO: implement intFromJNI()
}

extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_yang_natives_NativeBean_floatFromJIN(JNIEnv *env, jobject, jobjectArray age) {
    // TODO: implement floatFromJIN()
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_yang_natives_NativeBean_classFromJin(JNIEnv *env, jobject instance, jobject bean) {
    /**
     * 反射java方法
     */
    jclass beanClass = env->GetObjectClass(bean);
    //sig参数 签名
    env->GetMethodID(beanClass, "getName", "()Ljava/lang/String;");

    /**
     * 反射属性
     */
    jclass  clazz =env->GetObjectClass(instance);

    //获得int a的标示
    jfieldID a = env->GetStaticFieldID(clazz,"eventId", "Ljava/lang/String;");
    env->SetStaticObjectField(clazz,a,env->NewStringUTF("23232"));

}