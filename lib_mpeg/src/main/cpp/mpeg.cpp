#include <jni.h>
#include <string>
extern "C"{
#include "libavutil/avutil.h"
#include "android/log.h"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, "LOG_TAG", __VA_ARGS__)
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_mpeg_NativeLib_stringFromJNI(JNIEnv *env, jobject thiz) {

    LOGD("this is a jni log");
    av_log_set_level(AV_LOG_DEBUG);
    av_log(nullptr, AV_LOG_DEBUG,"av log");
    return env->NewStringUTF(av_version_info());
}