package com.hearthappy.encryption.target

/**
 * Created Date 2021/1/12.
 * @author ChenRui
 * ClassDescription:
 */
sealed class EncryptionMode {
    abstract fun ts(): String

    object CBC : EncryptionMode() {
        override fun ts(): String = "CBC"
    }

    object ECB : EncryptionMode() {
        override fun ts(): String = "ECB"
    }

    object CTR : EncryptionMode() {
        override fun ts(): String = "CTR"
    }

    object OFB : EncryptionMode() {
        override fun ts(): String = "OFB"
    }

    object CFB : EncryptionMode() {
        override fun ts(): String = "CFB"
    }

}
