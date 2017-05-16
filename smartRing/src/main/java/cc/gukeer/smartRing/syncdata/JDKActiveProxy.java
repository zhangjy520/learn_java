package cc.gukeer.smartRing.syncdata;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/2/16.
 */
public class JDKActiveProxy implements InvocationHandler {
    private Object target;

    //绑定委托对象，并返回这个对象的一个代理类  Proxy.newPoxyInstance()返回这个对象的代理类，代理对象
    public Object getInstance(Object target) {
        this.target = target;

        //取得代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }


    /*
     代理对象调用方法的时候会调用invoke,这里才是真正的实现
     proxy:　　指代我们所代理的那个真实对象
    method:　　指代的是我们所要调用真实对象的某个方法的Method对象
    args:　　指代的是调用真实对象某个方法时接受的参数*/
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        result = method.invoke(target, args);
        return result;
    }
}
