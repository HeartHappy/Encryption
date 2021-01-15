package com.hearthappy.encryption.target

/**
 * Created Date 2021/1/12.
 * @author ChenRui
 * ClassDescription:
 */
class Config(var encryptionMode: EncryptionMode, var padding: Padding, var offset: Offset, var output: Output, var characterSet: CharacterSet) {
    constructor(encryptionMode: EncryptionMode, padding: Padding, offset: Offset, output: Output) : this(encryptionMode, padding, offset, output, CharacterSet.UTF_8)
}


