package com.hearthappy.encryption.target

/**
 * Created Date 2021/1/12.
 * @author ChenRui
 * ClassDescription:
 */
sealed class Offset {
    abstract fun ti(): Int

    object NotOffset : Offset() {
        override fun ti(): Int = 0
    }

    object Offset16 : Offset() {
        override fun ti(): Int = 16
    }

    object Offset32 : Offset() {
        override fun ti(): Int = 32
    }
}
