# ButterKnife_demo
自定义实现ButterKnife功能  注解反射动态代理


使用方式

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity

 @BindId(R.id.tv_hello)
 private TextView tv_hello;
 
  @Onclick({R.id.tv_hello})
    public void onClick(View view){
    
    
    
自定义注解 ContentView 反射注入布局
    
自定义注解 BindId 反射注入控件id 并赋值给属性

自定义注解Onclick 并给注解设置Bean 监听方法,监听的类的类型,监听的回调方法
通过反射给onclick方法传入此三个值 用动态代理 切面view的onclcik 方法替换自己写的click方法
动态代理还实现了 多次点击的全局拦截.

原理 是反射实现注入 动态代理实现拦截切面方法替换 全局拦截
     
     
