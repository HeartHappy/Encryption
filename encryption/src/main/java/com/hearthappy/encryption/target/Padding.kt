package com.hearthappy.encryption.target

/**
 * Created Date 2021/1/12.
 * @author ChenRui
 * ClassDescription:
 */
sealed class Padding {
    abstract fun ts(): String

    object PKCS5PADDING : Padding() {
        override fun ts(): String = "PKCS5PADDING"
    }

    object PKCS7PADDING : Padding() {
        override fun ts(): String = "PKCS7PADDING"
    }

    object NO_Padding : Padding() {
        override fun ts(): String = "NO_Padding"
    }
}
