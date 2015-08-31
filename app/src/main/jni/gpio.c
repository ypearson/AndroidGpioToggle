#include <string.h>
#include <jni.h>
#include <stdio.h>
#include "gpio.h"


jint var;

jint JNI_OnLoad(JavaVM* pVM, void* reserved)
{
    var = 0;
    return JNI_VERSION_1_6;
}

void Java_com_nklabs_ypearson_gpiotoggle_MainActivity_initgpio(JNIEnv* pEnv, jobject pThis)
{

}
jstring Java_com_nklabs_ypearson_gpiotoggle_MainActivity_readgpio(JNIEnv* pEnv, jobject pThis, jstring path)
{
    int i;
    char *cfile = (*pEnv)->GetStringUTFChars(pEnv, path, 0);

    char buffer [32];
    memset(buffer, 0, 32);

    FILE * pFile = fopen (cfile , "r");

    if (pFile != NULL)
    {
        fgets (buffer, 32, pFile);
        fclose (pFile);
    }
    return (*pEnv)->NewStringUTF(pEnv, buffer);
}

void Java_com_nklabs_ypearson_gpiotoggle_MainActivity_writegpio(JNIEnv* pEnv, jobject pThis, jstring path, jstring val)
{
    char *cfile = (*pEnv)->GetStringUTFChars(pEnv, path, 0);
    char *cval =  (*pEnv)->GetStringUTFChars(pEnv, val,  0);

    FILE* fd = fopen(cfile,"w+");

    if (fd != NULL)
    {
        fputs(cval, fd);
        fflush(fd);
        fclose(fd);
    }
}



void Java_com_nklabs_ypearson_gpiotoggle_MainActivity_setgpio(JNIEnv* pEnv, jobject pThis, jint num)
{
    var = num;
}

jint Java_com_nklabs_ypearson_gpiotoggle_MainActivity_getgpio(JNIEnv* pEnv, jobject pThis, jint num)
{
    return var;
}