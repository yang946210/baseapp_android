#include <jni.h>
#include <string>
extern "C"{
#include "libavutil/avutil.h"
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_mpeg_NativeLib_stringFromJNI(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF(av_version_info());
}