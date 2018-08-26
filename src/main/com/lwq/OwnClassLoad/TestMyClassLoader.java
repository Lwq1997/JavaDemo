package main.com.lwq.OwnClassLoad;

/**
 * @Author: Lwq
 * @Date: 2018/8/26 16:11
 * @Version 1.0
 * @Describe
 */
public class TestMyClassLoader {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader mcl = new MyClassLoader(ClassLoader.getSystemClassLoader().getParent());
        Class<?> cl = Class.forName("main.com.lwq.OwnClassLoad.Person",true,mcl);
        Object obj = cl.newInstance();
        System.out.println(obj);
        System.out.println(obj.getClass().getClassLoader());
    }
}
