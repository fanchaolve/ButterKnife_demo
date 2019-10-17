package com.fancl.knifelibrary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventBase {

    //监听方法
    String listerSetter();

    //监听的类的类型
    Class<?> listerType();

    //监听的回调方法
    String callBackListener();
}
