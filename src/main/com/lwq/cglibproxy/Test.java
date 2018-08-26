package main.com.lwq.cglibproxy;

/**
 * @Author: Lwq
 * @Date: 2018/8/26 20:33
 * @Version 1.0
 * @Describe
 */
public class Test {
    public static void main(String[] args) {
        IMath math=(IMath)new CglibDynamicProxy().getProxyObject(new Math());
        int n1 = 100;
        int n2 = 5;
        math.add(n1,n2);
        math.sub(n1,n2);
        math.mut(n1,n2);
        math.div(n1,n2);
    }
}
