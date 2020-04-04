package com.biz.spring.framework.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xjn
 * @since 2020-03-11
 * JDK动态代理
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private Object target;

    private AopConfig aopConfig;

    public void setConfig(AopConfig aopConfig) {
        this.aopConfig = aopConfig;
    }

    @Override
    public Object getProxy(Object instance) {
        this.target = instance;
        Class<?> aClass = instance.getClass();
        return Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在原始方法调用之前执行增强的代码
        if (aopConfig.contains(method)) {
            Aspect aspect = aopConfig.get(method);
            aspect.getPoints()[0].invoke(aspect.getAspect(), args);
        }
        Object invoke = method.invoke(this.target, args);
        //在原始方法调用之后执行增强的代码
        if (aopConfig.contains(method)) {
            Aspect aspect = aopConfig.get(method);
            aspect.getPoints()[1].invoke(aspect.getAspect(), args);
        }
        //返回原始的返回值
        return invoke;
    }
}
