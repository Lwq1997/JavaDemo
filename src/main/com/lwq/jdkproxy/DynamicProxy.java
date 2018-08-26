package main.com.lwq.jdkproxy;

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
public class DynamicProxy implements InvocationHandler {

    //被代理的对象
    Object target;

    /**
     * 获得代理后的对象
     * @param object  被代理的对象
     * @return  代理后的对象
     */
    public Object getProxyObject(Object object){
        this.target = object;
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),//类加载器
                target.getClass().getInterfaces(),//获得被代理对象的所有接口
                this);
        /*
          loader:一个ClassLoader对象，定义了由哪个ClassLoader对象来对生成的代理对象进行加载
28        interfaces:一个Interface对象的数组，表示的是我将要给我需要代理的对象提供一组什么接口，如果我提供了一组接口给它，那么这个代理对象就宣称实现了该接口(多态)，这样我就能调用这组接口中的方法了
29        h:一个InvocationHandler对象，表示的是当我这个动态代理对象在调用方法的时候，会关联到哪一个InvocationHandler对象上，间接通过invoke来执行
         */
    }

    /**
     *
     * @param proxy     被代理后的对象
     * @param method    将要被执行的方法信息
     * @param args      执行方法是需要的参数
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //被织入的内容
        long start=System.currentTimeMillis();
        lazy();

        Object result = method.invoke(target,args);

        Long span= System.currentTimeMillis()-start;
        System.out.println("共用时："+span);

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
