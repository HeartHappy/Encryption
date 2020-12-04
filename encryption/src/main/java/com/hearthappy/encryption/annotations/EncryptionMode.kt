package com.hearthappy.encryption.annotations

import androidx.annotation.StringDef

/**
 * Created Date 2020/12/1.
 * @author ChenRui
 * ClassDescription:AES加密模式
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
@StringDef(
    EncryptionMode.CBC,//该模式需要16位数密钥
    EncryptionMode.ECB,
    EncryptionMode.CTR,
    EncryptionMode.OFB,
    EncryptionMode.CFB
)
annotation class EncryptionMode {
    companion object {
        const val CBC = "CBC"
        const val ECB = "ECB"
        const val CTR = "CTR"
        const val OFB = "OFB"
        const val CFB = "CFB"
    }
}