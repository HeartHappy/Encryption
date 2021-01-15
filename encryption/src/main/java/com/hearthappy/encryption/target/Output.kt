package com.hearthappy.encryption.target

/**
 * Created Date 2021/1/12.
 * @author ChenRui
 * ClassDescription:
 */
sealed class Output {
    abstract fun ts(): String

    object Base64 : Output() {
        override fun ts(): String = "base64"
    }

    object Hex : Output() {
        override fun ts(): String = "hex"
    }
}
