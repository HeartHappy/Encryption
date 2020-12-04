package com.hearthappy.encryption.annotations

import androidx.annotation.IntDef

/**
 * Created Date 2020/12/2.
 * @author ChenRui
 * ClassDescription:偏移量
 */
@IntDef(
    Offset.default,
    Offset._16,
    Offset._32
)
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD)
annotation class Offset {
    companion object {
        const val default = 0
        const val _16 = 16
        const val _32 = 32
    }
}
