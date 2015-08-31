#include <string.h>
#include <jni.h>
#include "gpio.h"
static jint var;

jint
JNI_OnLoad(JavaVM* pVM, void* reserved) {

    var = 0;
    return JNI_VERSION_1_6;
}

jstring
Java_com_nklabs_ypearson_gpiotoggle_MainActivity_stringFromJNI(JNIEnv* pEnv, jobject pThis )
{
#if defined(__arm__)
    #if defined(__ARM_ARCH_7A__)
      #if defined(__ARM_NEON__)
        #if defined(__ARM_PCS_VFP)
          #define ABI "armeabi-v7a/NEON (hard-float)"
        #else
          #define ABI "armeabi-v7a/NEON"
        #endif
      #else
        #if defined(__ARM_PCS_VFP)
          #define ABI "armeabi-v7a (hard-float)"
        #else
          #define ABI "armeabi-v7a"
        #endif
      #endif
    #else
     #define ABI "armeabi"
    #endif
#elif defined(__i386__)
    #define ABI "x86"
#elif defined(__x86_64__)
    #define ABI "x86_64"
#elif defined(__mips64)  /* mips64el-* toolchain defines __mips__ too */
    #define ABI "mips64"
#elif defined(__mips__)
    #define ABI "mips"
#elif defined(__aarch64__)
#define ABI "arm64-v8a"
#else
    #define ABI "unknown"
#endif

    return (*pEnv)->NewStringUTF(pEnv, ABI);
}

void Java_com_nklabs_ypearson_gpiotoggle_MainActivity_setgpio(JNIEnv* pEnv, jint num)
{
    var = num;


}

jint Java_com_nklabs_ypearson_gpiotoggle_MainActivity_getgpio(JNIEnv* pEnv, jint num)
{
    return var;

}