package com.hearthappy.encryption;

import org.junit.Test;

/**
 * Created Date 2021/1/12.
 *
 * @author ChenRui
 * ClassDescription:
 */
class EUT {

    @Test
    public void test(){
//        String e = Codec2.AES.INSTANCE.e("0123456789012345", "123456", new Config(EncryptionMode.CBC, Padding.PKCS5PADDING, Offset.Offset16, Output.Hex, CharacterSet.UTF_8));
//        System.out.println("e:"+e);
        System.out.println(CodecX.MD5.e("123456"));
    }
}
