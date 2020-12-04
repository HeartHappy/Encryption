package com.hearthappy.encryption.annotations

import androidx.annotation.StringDef

/**
 * Created Date 2020/12/3.
 * @author ChenRui
 * ClassDescription:输出格式，分别为为：base64、hex
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD)
@StringDef(Output.BASE64, Output.HEX)
annotation class Output {
    companion object {
        const val BASE64 = "base64"
        const val HEX = "hex"
    }
}