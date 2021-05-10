package com.hearthappy.encryption

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hearthappy.encryption.target.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btnEncoded.setOnClickListener {
            startCodecX(true)
        }

        btnDecode.setOnClickListener {
            startCodecX(false)
        }
    }

    private fun startCodecX(isEncode: Boolean) {
        val content = etContent.text.toString()
        val key = etKey.text.toString()
        if (content.isNotBlank() && key.isNotEmpty()) {
            val tvAlgorithm = spAlgorithm.selectedView as TextView
            val result = when (tvAlgorithm.text.toString()) {
                Algorithm.AES.ts() -> {
                    if (isEncode) {
                        Codec.AES.e(key, content, getConfig())
                    } else {
                        Codec.AES.d(key, content, getConfig())
                    }
                }
                else -> {
                    if (isEncode) {
                        Codec.DES.e(key, content, getConfig())
                    } else {
                        Codec.DES.d(key, content, getConfig())
                    }
                }
            }
            tvResult.text = result
        } else {
            Toast.makeText(this, "内容或密钥不能为空", Toast.LENGTH_SHORT).show()
        }

    }

    private fun getConfig(): Config {

        val tvEncryptionMode = spEncryptionMode.selectedView as TextView
        val tvFilling = spFilling.selectedView as TextView
        val tvOffset = etOffset.text.toString()
        val tvOutput = spOutput.selectedView as TextView
        val tvCharacterSet = spCharacterSet.selectedView as TextView

        val encryptionMode = getEncryptionMode(tvEncryptionMode.text.toString())
        val padding = getPadding(tvFilling.text.toString())
        val output = getOutput(tvOutput.text.toString())
        val characterSet = getCharacterSet(tvCharacterSet.text.toString())
        return if (tvOffset.isNotBlank()) {
            Config(encryptionMode, padding, tvOffset, output, characterSet)
        } else {
            Config(
                encryptionMode = encryptionMode,
                padding = padding,
                output = output,
                characterSet = characterSet
            )
        }
    }

    private fun getCharacterSet(characterSet: String): CharacterSet {
        return when (characterSet) {
            CharacterSet.UTF_8.ts() -> {
                CharacterSet.UTF_8
            }
            CharacterSet.ISO10126Padding.ts() -> {
                CharacterSet.ISO10126Padding
            }
            else -> {
                CharacterSet.ISO_8859_1
            }
        }
    }


    private fun getOutput(output: String): Output {
        return when (output) {
            Output.Hex.ts() -> {
                Output.Hex
            }
            else -> {
                Output.Base64
            }
        }
    }


    private fun getEncryptionMode(encryptionMode: String): EncryptionMode {
        return when (encryptionMode) {
            EncryptionMode.CBC.ts() -> {
                EncryptionMode.CBC
            }
            EncryptionMode.CFB.ts() -> {
                EncryptionMode.CFB
            }
            EncryptionMode.CTR.ts() -> {
                EncryptionMode.CTR
            }
            EncryptionMode.ECB.ts() -> {
                EncryptionMode.ECB
            }
            EncryptionMode.OFB.ts() -> {
                EncryptionMode.OFB
            }
            else -> EncryptionMode.CBC
        }
    }

    private fun getPadding(filling: String): Padding {
        return when (filling) {
            Padding.PKCS5PADDING.ts() -> {
                Padding.PKCS5PADDING
            }
            Padding.PKCS7PADDING.ts() -> {
                Padding.PKCS7PADDING
            }
            else -> {
                Padding.NO_Padding
            }
        }
    }
}