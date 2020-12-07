package com.hearthappy.encryption

import com.hearthappy.encryption.annotations.*
import org.apache.commons.codec.binary.Base64
import org.apache.commons.codec.binary.Hex
import org.jetbrains.annotations.NotNull
import java.nio.charset.Charset
import java.security.Key
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESKeySpec
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * Created Date 2020/12/2.
 * @author ChenRui
 * ClassDescription:支持AES、DES
 */
class Codec {
    companion object {
        @Algorithm
        private var algorithm = Algorithm.AES //加密算法
        private var encryptionMode = EncryptionMode.CBC //加密模式
        private var filling = Filling.PKCS5PADDING //填充
        private var offset = Offset._16 //偏移量
        private var output = Output.HEX //输出类型
        private var characterSet = CharacterSet.UTF_8  //字符集

        @NotNull
        @JvmStatic
        fun config(
            @Algorithm algorithm: String,
            @EncryptionMode
            encryptionMode: String,
            @NotNull
            @Filling
            filling: String,
            @NotNull
            @Offset
            offset: Int,
            @NotNull
            @Output
            output: String,
            @CharacterSet
            characterSet: String
        ): Companion {
            Companion.algorithm = algorithm
            Companion.encryptionMode = encryptionMode
            Companion.filling = filling
            Companion.offset = offset
            Companion.output = output
            Companion.characterSet = characterSet
            return this
        }

        @JvmStatic
        fun encode(@NotNull key: String, @NotNull password: String): String? {
            try {
                //iv:偏移量
                val iv = if (offset != Offset.default) {
                    IvParameterSpec(
                        offset(
                            offset
                        ).toByteArray(Charset.forName(CharacterSet.UTF_8)))
                } else {
                    null
                }
                //通过工厂生成key
                val keyFactory =
                    generateKey(key)
                val cipher = Cipher.getInstance("$algorithm/$encryptionMode/$filling")
                iv?.let {
                    cipher.init(Cipher.ENCRYPT_MODE, keyFactory, iv)
                } ?: let {
                    cipher.init(Cipher.ENCRYPT_MODE, keyFactory)
                }
                if (output == Output.BASE64) {
                    return encodeBase64ToString(
                        cipher.doFinal(password.toByteArray())
                    )
                }
                return String(Hex.encodeHex(cipher.doFinal(password.toByteArray())))
            } catch (ex: Exception) {
                return "解析错误：$ex"
            }
        }

        @JvmStatic
        fun decode(@NotNull key: String, @NotNull encrypted: String): String? {
            try {
                var iv: IvParameterSpec? = null
                val keySpec =
                    generateKey(key)
                if (offset != Offset.default) {
                    iv =
                        IvParameterSpec(
                            offset(
                                offset
                            ).toByteArray(Charset.forName(CharacterSet.UTF_8)))
                }

                val cipher = Cipher.getInstance("$algorithm/$encryptionMode/$filling")

                iv?.let {
                    cipher.init(Cipher.DECRYPT_MODE, keySpec, iv)
                } ?: let {
                    cipher.init(Cipher.DECRYPT_MODE, keySpec)
                }
                if (output == Output.BASE64) {
                    return String(cipher.doFinal(
                        decodeBase64ToBytes(
                            encrypted
                        )
                    ))
                }
                return String(cipher.doFinal(Hex.decodeHex(encrypted.toCharArray())))
            } catch (ex: Exception) {
                return "解析错误：$ex"
            }
        }


        /**
         * 偏移量：传入16的倍数或16
         */
        private fun offset(@Offset offset: Int): String {
            if (offset < 16) {
                throw InterruptedException("Minimum offset: 16 bytes in length")
            } else if (offset % 16 != 0) {
                throw InterruptedException("The offset must be a multiple of 16")
            }
            val sb = StringBuffer()
            for (i in 0 until offset) {
                sb.append("0")
            }
            return sb.toString()
        }


        /**
         * 输出Base64：加密字节数组并转为base64字符串
         */
        private fun encodeBase64ToString(bytes: ByteArray): String {
            return String(Base64.encodeBase64(bytes))
        }


        /**
         * 输出Base64：解析Base64字符串并转换为字节
         */
        private fun decodeBase64ToBytes(@NotNull data: String): ByteArray {
            return Base64.decodeBase64(data.toByteArray())
        }

        private fun generateKey(key: String): Key {
            when (algorithm) {
                Algorithm.AES -> {
                    return SecretKeySpec(
                        key.toByteArray(Charset.forName(CharacterSet.UTF_8)),
                        algorithm
                    )
                }
                Algorithm.DES -> {
                    return SecretKeyFactory.getInstance(algorithm)
                        .generateSecret(DESKeySpec(key.toByteArray()))
                }
                else -> {
                    return SecretKeySpec(
                        key.toByteArray(Charset.forName(CharacterSet.UTF_8)),
                        algorithm
                    )
                }
            }
        }
    }
}