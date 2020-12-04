package com.hearthappy.encryption.annotations;

import androidx.annotation.StringDef;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created Date 2020/12/4.
 *
 * @author ChenRui
 * ClassDescription:算法
 */
@StringDef({Algorithm.AES, Algorithm.DES})
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.ANNOTATION_TYPE,ElementType.PARAMETER,ElementType.FIELD,ElementType.LOCAL_VARIABLE})
public @interface Algorithm {
    String AES = "AES";
    String DES = "DES";
}
