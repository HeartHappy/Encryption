package com.hearthappy.encryption

import com.hearthappy.encryption.target.*
import org.apache.commons.codec.binary.Base64
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import org.jetbrains.annotations.NotNull
import java.io.File
import java.nio.charset.Charset
import java.security.Key
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESKeySpec
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * Created Date 2021/1/12.
 * @author ChenRui
 * ClassDescription:加解密
 */
sealed class CodecX {


    abstract fun e(@NotNull key: String, @NotNull password: String, initConfig: Config): String

    abstract fun d(@NotNull key: String, @NotNull encrypted: String, initConfig: Config): String

    object AES : CodecX() {
        override fun e(key: String, password: String, initConfig: Config): String {
            return encode(Algorithm.AES, initConfig, key, password)
        }

        override fun d(key: String, encrypted: String, initConfig: Config): String {
            return decode(Algorithm.AES, initConfig, key, encrypted)
        }
    }

    object DES : CodecX() {
        override fun e(key: String, password: String, initConfig: Config): String {
            return encode(Algorithm.DES, initConfig, key, password)
        }

        override fun d(key: String, encrypted: String, initConfig: Config): String {
            return decode(Algorithm.DES, initConfig, key, encrypted)
        }
    }

    object MD5 {
        @JvmStatic fun e(text: String): String {
            return md5Encode(text)
        }

        @JvmStatic fun ef(file: File): String {
            return md5FileEncode(file)
        }

        @JvmStatic infix fun encode(text: String): String {
            return md5Encode(text)
        }

        @JvmStatic infix fun encodeFile(file: File): String {
            return md5FileEncode(file)
        }
    }


    companion object {

        val defaultConfig: Config by lazy { Config() }

        private fun encode(algorithm: Algorithm, c: Config, key: String, password: String): String {
            try {
                //iv:偏移量
                val iv = if (c.offset.isNotBlank()) {
                    IvParameterSpec(verOffset(c.offset).toByteArray(Charset.forName(c.characterSet.ts())))
                } else {
                    null
                }
                //通过工厂生成key
                val keyFactory = generateKey(algorithm, key)
                val cipher = Cipher.getInstance("${algorithm.ts()}/${c.encryptionMode.ts()}/${c.padding.ts()}")
                iv?.let {
                    cipher.init(Cipher.ENCRYPT_MODE, keyFactory, iv)
                } ?: let {
                    cipher.init(Cipher.ENCRYPT_MODE, keyFactory)
                }
                if (c.output == Output.Base64) {
                    return encodeBase64ToString(cipher.doFinal(password.toByteArray()))
                }
                return String(Hex.encodeHex(cipher.doFinal(password.toByteArray())))
            } catch (ex: Exception) {
                return "解析错误:$ex"
            }
        }

        private fun decode(algorithm: Algorithm, c: Config, key: String, encrypted: String): String {
            try {
                var iv: IvParameterSpec? = null
                val keySpec = generateKey(algorithm, key)
                if (c.offset.isNotBlank()) {
                    iv = IvParameterSpec(verOffset(c.offset).toByteArray(Charset.forName(c.characterSet.ts())))
                }
                val cipher = Cipher.getInstance("${algorithm.ts()}/${c.encryptionMode.ts()}/${c.padding.ts()}")

                iv?.let {
                    cipher.init(Cipher.DECRYPT_MODE, keySpec, iv)
                } ?: let {
                    cipher.init(Cipher.DECRYPT_MODE, keySpec)
                }
                if (c.output == Output.Base64) {
                    return String(cipher.doFinal(decodeBase64ToBytes(encrypted)))
                }
                return String(cipher.doFinal(Hex.decodeHex(encrypted.toCharArray())))
            } catch (ex: Exception) {
                return "解析错误:$ex"
            }
        }

        private fun md5Encode(text: String): String {
            return try {
                DigestUtils.md5Hex(text)
            } catch (e: Exception) {
                "解析错误:$e"
            }
        }

        private fun md5FileEncode(file: File): String {
            if (!file.exists() || !file.isFile) {
                return "解析错误:文件不存在"
            }
            return try {
                DigestUtils.md5Hex(file.readBytes())
            } catch (e: Exception) {
                "解析错误:$e"
            }
        }

        /**
         * 偏移量：传入16的倍数或16
         */
        private fun verOffset(offset: String): String {
            if (offset.length in 1..15) {
                throw InterruptedException("Minimum offset: 16 bytes in length")
            }
            return offset
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

        private fun generateKey(algorithm: Algorithm, key: String): Key {
            return when (algorithm) {
                Algorithm.AES -> {
                    SecretKeySpec(key.toByteArray(Charset.forName(CharacterSet.UTF_8.ts())), algorithm.ts())
                }
                Algorithm.DES -> {
                    SecretKeyFactory.getInstance(algorithm.ts()).generateSecret(DESKeySpec(key.toByteArray()))
                }
            }
        }
    }
}