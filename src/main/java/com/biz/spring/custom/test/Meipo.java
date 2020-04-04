package com.biz.spring.custom.test;

import com.biz.spring.custom.proxy.GPClassLoader;
import com.biz.spring.custom.proxy.InvocationHandler;
import com.biz.spring.custom.proxy.Proxy;

import java.lang.reflect.Method;

/**
 * @author xjn
 * @since 2020-03-11
 */
public class Meipo implements InvocationHandler {
    private Person target;

    //获取被代理人的个人资料
    public Object getInstance(Person target) throws Exception{
        this.target = target;
        Class clazz = target.getClass();
        System.out.println("被代理对象的class是:"+clazz);
        return Proxy.newProxyInstance(new GPClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是媒婆：得给你找个异性才行");
        System.out.println("开始进行海选...");
        System.out.println("------------");
        method.invoke(this.target, args);
        System.out.println("------------");
        System.out.println("如果合适的话，就准备办事");
        return null;
    }

}
