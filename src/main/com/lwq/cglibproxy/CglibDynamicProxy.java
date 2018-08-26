package main.com.lwq.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;

/**
 * @Author: Lwq
 * @Date: 2018/8/26 20:31
 * @Version 1.0
 * @Describe
 */
public class CglibDynamicProxy implements MethodInterceptor {

    //被代理的对象
    Object target;

    /**
     * 动态生成一个新类，使用父类（被代理类）的无参构造器
     * @param object
     * @return
     */
    public Object getProxyObject(Object object){
        this.target = object;
        //增强器
        Enhancer enhancer = new Enhancer();
        //回调方法
        enhancer.setCallback(this);
        //设置生成类的父类
        enhancer.setSuperclass(target.getClass());
        //动态生成自己饿么并返回代理对象
        return enhancer.create();
    }

    /**
     * 拦截方法
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // 被织入的横切内容，开始时间 before
        long start = System.currentTimeMillis();
        lazy();

        // 调用方法
        Object result = methodProxy.invoke(target, args);

        // 被织入的横切内容，结束时间
        Long span = System.currentTimeMillis() - start;
        System.out.println("共用时：" + span);

        return result;
    }

    //模拟延时
    public void lazy()
    {
        try {
            int n=(int)new Random().nextInt(500);
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
