package com.hearthappy.encryption.target

/**
 * Created Date 2021/1/12.
 * @author ChenRui
 * ClassDescription:
 */
class Config(
    var encryptionMode: EncryptionMode = EncryptionMode.CBC,
    var padding: Padding = Padding.PKCS5PADDING,
    var offset: String="0000000000000000",
    var output: Output = Output.Hex,
    var characterSet: CharacterSet = CharacterSet.UTF_8
)


