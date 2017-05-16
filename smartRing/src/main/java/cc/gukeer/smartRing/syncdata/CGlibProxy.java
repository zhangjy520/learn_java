package cc.gukeer.smartRing.syncdata;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/2/16.
 */
public class CGlibProxy implements MethodInterceptor {
    private Object target;

    public Object getInstance(Object target){
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        //回调方法
        enhancer.setCallback(this);//设置this,代理对象调用方法访问intercept如下
        //创建代理对象
        return enhancer.create();
    }

    //代理对象调用它的成员方法，会转为调用intercept方法
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = methodProxy.invokeSuper(o,objects);
        return result;
    }
}
