package com.hearthappy.encryption.annotations

import androidx.annotation.StringDef

/**
 * Created Date 2020/12/1.
 * @author ChenRui
 * ClassDescription:字符集
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@StringDef(
    CharacterSet.UTF_8,
    CharacterSet.ISO10126Padding,
    CharacterSet.ISO_8859_1
)
annotation class CharacterSet {
    companion object {
        const val UTF_8 = "UTF-8"
        const val ISO_8859_1 = "ISO_8859_1"
        const val ISO10126Padding = "ISO10126Padding"
    }
}