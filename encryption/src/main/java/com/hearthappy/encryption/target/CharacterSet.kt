package com.hearthappy.encryption.target

/**
 * Created Date 2021/1/12.
 * @author ChenRui
 * ClassDescription:
 */
sealed class CharacterSet {
    abstract fun ts(): String

    object UTF_8 : CharacterSet() {
        override fun ts(): String = "UTF-8"
    }

    object ISO_8859_1 : CharacterSet() {
        override fun ts(): String = "ISO_8859_1"
    }

    object ISO10126Padding : CharacterSet() {
        override fun ts(): String = "ISO10126Padding"
    }
}

