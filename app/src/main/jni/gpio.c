#include <string.h>
#include <jni.h>
#include "gpio.h"


jint var;

jint JNI_OnLoad(JavaVM* pVM, void* reserved)
{
    var = 0;
    return JNI_VERSION_1_6;
}

void Java_com_nklabs_ypearson_gpiotoggle_MainActivity_initgpio(JNIEnv* pEnv, jobject pThis)
{
    var = 1;
}

void Java_com_nklabs_ypearson_gpiotoggle_MainActivity_setgpio(JNIEnv* pEnv, jobject pThis, jint num)
{
    var = num;
}

jint Java_com_nklabs_ypearson_gpiotoggle_MainActivity_getgpio(JNIEnv* pEnv, jobject pThis, jint num)
{
    return var;
}