package com.hearthappy.encryption.annotations

import androidx.annotation.StringDef

/**
 * Created Date 2020/12/1.
 * @author ChenRui
 * ClassDescription:填充
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@StringDef(
    Filling.NO_Padding,
    Filling.PKCS5PADDING,
    Filling.PKCS7PADDING
)
annotation class Filling {

    companion object {
        const val PKCS5PADDING = "PKCS5PADDING"
        const val PKCS7PADDING = "PKCS7PADDING"
        const val NO_Padding = "NoPadding"
    }
}