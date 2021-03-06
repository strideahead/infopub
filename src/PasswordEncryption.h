/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class PasswordEncryption */

#ifndef _Included_PasswordEncryption
#define _Included_PasswordEncryption
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     PasswordEncryption
 * Method:    make_32
 * Signature: (Ljava/lang/String;I)I
 */
JNIEXPORT jint JNICALL Java_PasswordEncryption_make_132
  (JNIEnv *, jclass, jstring, jint);

/*
 * Class:     PasswordEncryption
 * Method:    set_seedkey
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_PasswordEncryption_set_1seedkey
  (JNIEnv *, jclass, jstring);

/*
 * Class:     PasswordEncryption
 * Method:    make_encrypt
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_PasswordEncryption_make_1encrypt
  (JNIEnv *, jclass, jstring);

#ifdef __cplusplus
}
#endif
#endif
