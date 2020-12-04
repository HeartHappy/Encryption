package com.hearthappy.encryption.annotations

import androidx.annotation.StringDef

/**
 * Created Date 2020/12/4.
 *
 * @author ChenRui
 * ClassDescription:算法
 */

@Retention(AnnotationRetention.SOURCE)
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