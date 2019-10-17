package com.fancl.knifelibrary;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventBase(listerSetter = "setOnClickListener",
        listerType = View.OnClickListener.class,
        callBackListener = "onClick"
)
public @interface Onclick {

    //id可能为多个 设计成数组
    int[] value();
}
