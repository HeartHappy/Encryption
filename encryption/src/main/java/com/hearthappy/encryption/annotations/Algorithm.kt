package com.hearthappy.encryption.annotations

import androidx.annotation.StringDef
import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Created Date 2020/12/4.
 *
 * @author ChenRui
 * ClassDescription:算法
 */

@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.FIELD,
    AnnotationTarget.LOCAL_VARIABLE
)
@StringDef(
    Algorithm.AES,
    Algorithm.DES
)
annotation class Algorithm {
    companion object {
        const val AES = "AES"
        const val DES = "DES"
    }
}