package com.hearthappy.encryption.target

/**
 * Created Date 2021/1/12.
 * @author ChenRui
 * ClassDescription:
 */
sealed class Algorithm {
    abstract fun ts(): String

    object AES : Algorithm() {
        override fun ts(): String = "AES"
    }

    object DES : Algorithm() {
        override fun ts(): String = "DES"
    }
}