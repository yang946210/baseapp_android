#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring
Java_com_example_mpeg_NativeLib_stringFromJNI(JNIEnv* env,jobject /* this */) {
    std::string hello = "Hello from C++1112";
    return env->NewStringUTF(hello.c_str());
}