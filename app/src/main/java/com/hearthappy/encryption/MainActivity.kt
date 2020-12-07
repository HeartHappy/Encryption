package com.hearthappy.encryption

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hearthappy.encryption.annotations.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnEncoded.setOnClickListener {
            val content = etContent.text.toString()
            val key = etKey.text.toString()
            if (content.isNotBlank() && key.isNotEmpty()) {
                val codec = getCodec()
                val encode = codec.encode(key, content)
                tvResult.text = encode
            } else {
                Toast.makeText(this, "内容或密钥不能为空", Toast.LENGTH_SHORT).show()
            }
        }

        btnDecode.setOnClickListener {
            val content = etContent.text.toString()
            val key = etKey.text.toString()
            if (content.isNotBlank() && key.isNotEmpty()) {
                val codec = getCodec()
                val encode = codec.decode(key, content)
                tvResult.text = encode
            } else {
                Toast.makeText(this, "内容或密钥不能为空", Toast.LENGTH_SHORT).show()
            }
        }
//        test()
    }

    private fun getCodec(): Codec.Companion {
        val tvAlgorithm = spAlgorithm.selectedView as TextView
        val tvEncryptionMode = spEncryptionMode.selectedView as TextView
        val tvFilling = spFilling.selectedView as TextView
        val tvOffset = spOffset.selectedView as TextView
        val tvOutput = spOutput.selectedView as TextView
        val tvCharacterSet = spCharacterSet.selectedView as TextView

        val offsetArray = resources.getStringArray(R.array.offset)
        var offset: Int = Offset.default
        when (tvOffset.text) {
            offsetArray[0] -> {
                offset = Offset.default
            }
            offsetArray[1] -> {
                offset = Offset._16
            }
            offsetArray[2] -> {
                offset = Offset._32
            }
        }
        return Codec.config(
            tvAlgorithm.text.toString(),
            tvEncryptionMode.text.toString(),
            tvFilling.text.toString(),
            offset,
            tvOutput.text.toString(),
            tvCharacterSet.text.toString()
        )
    }

    private fun test() {
        val decode = Codec.config(
            Algorithm.AES,
            EncryptionMode.CBC,
            Filling.PKCS5PADDING,
            Offset._16,
            Output.HEX,
            CharacterSet.UTF_8
        )
            .decode("vesystem", "lK9XS+3rVFWHQxh2O6JjUw==")
        Log.d("MainActivity", "onCreate decode: $decode")

        val encode = Codec.encode("0123456789012345", "123456")
        Log.d("MainActivity", "onCreate encode: $encode")
    }
}