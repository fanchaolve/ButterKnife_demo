package com.fancl.knifelibrary;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;

public class ListenerInvocationHandler implements InvocationHandler {

    private Object target;

    private final  static  long  Quick_Event_CLICK_TIME = 1000;
    private long last_time = 0;

    HashMap<String, Method> hashMap = new HashMap<>();


    public ListenerInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (target != null) {
            if (hashMap.containsKey(method.getName())) {
               method = hashMap.get(method.getName());

               long timeSpan = System.currentTimeMillis() - last_time;
               if(timeSpan <Quick_Event_CLICK_TIME){
                   Log.e("fancl","多次点击了");
                   return null;
               }
               last_time = System.currentTimeMillis();
               if(method.getGenericParameterTypes().length == 0)
                  return  method.invoke(target);
              return method.invoke(target,args);

            }
        }

        return null;
    }

    /**
     * 拦截回调方法
     *
     * @param callBack onclick
     * @param method   xxx
     */
    public void add(String callBack, Method method) {
        hashMap.put(callBack, method);
    }
}
