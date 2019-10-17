package com.fancl.knifelibrary;

import android.app.Activity;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InjectMangner {


    public static void init(Activity activity) {
        //注入布局
        initViews(activity);

        //注入属性id;
        initViewIds(activity);
        //注入点击方法
        initViewClick(activity);
    }


    private static void initViewClick(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();

        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            Onclick annotation = method.getAnnotation(Onclick.class);
            if (annotation != null) {
                //获取注解之上的注解
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType != null) {
                    EventBase eventBase = annotationType.getAnnotation(EventBase.class);
                    if (eventBase != null) {
                        //监听的方法名
                        String listerSetter = eventBase.listerSetter();
                        //监听的接口
                        Class<?> listerType = eventBase.listerType();

                        String callBack = eventBase.callBackListener();


                        try {
                            Method value = annotationType.getDeclaredMethod("value");

                            ListenerInvocationHandler listenerInvocationHandler = new ListenerInvocationHandler(activity);
                            listenerInvocationHandler.add(callBack,method);
                            Object proxyObj = Proxy.newProxyInstance(listerType.getClassLoader(),
                                    new Class[]{listerType},listenerInvocationHandler
                                    );

                            int[] valueIds = (int[]) value.invoke(annotation);
                            for (int valueId : valueIds) {
                                View viewById = activity.findViewById(valueId);
                                Method onclickMethod = viewById.getClass().getMethod(listerSetter, listerType);
                                onclickMethod.invoke(viewById,proxyObj);


                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

//
//                        int[] valueIds = annotation.value();
//                        for (int valueId : valueIds) {
//                            View viewById = activity.findViewById(valueId);
//
//                        }

                    }
                }

            }

        }


    }

    private static void initViewIds(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            BindId annotation = field.getAnnotation(BindId.class);
            if (annotation != null) {
                int valueId = annotation.value();
                try {
                    Method findViewById = aClass.getMethod("findViewById", int.class);
                    Object invoke = findViewById.invoke(activity, valueId);
                    field.setAccessible(true);
                    field.set(activity, invoke);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }


    private static void initViews(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        try {
//            Annotation[] annotations = aClass.getAnnotations();
//            for (Annotation annotation : annotations){
//                if(annotation instanceof ContentView){
//                    int resid = ((ContentView) annotation).value();
//                    Method setContentView = aClass.getMethod("setContentView", int.class);
//                    setContentView.invoke(activity,resid);
//                }
//            }

            //获取类上面的注解
            ContentView annotation = aClass.getAnnotation(ContentView.class);
            if (annotation != null) {
                int resId = annotation.value();
                Method setContentView = aClass.getMethod("setContentView", int.class);
                setContentView.invoke(activity, resId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
