package com.spring.framework.beans;

import com.spring.framework.aop.AopConfig;
import com.spring.framework.aop.AopProxy;
import com.spring.framework.aop.JdkDynamicAopProxy;
import com.spring.framework.core.FactoryBean;

/**
 * @author xjn
 * @since 2020-03-09
 */
public class BeanWrapper extends FactoryBean {

    private AopProxy aopProxy = new JdkDynamicAopProxy();

    //还会用到观察者模式
    //1.支持事件响应.会有一个监听

    private BeanPostProcessor postProcessor;

    public BeanPostProcessor getPostProcessor() {
        return postProcessor;
    }

    public void setPostProcessor(BeanPostProcessor postProcessor) {
        this.postProcessor = postProcessor;
    }

    private Object wrappedInstance;

    //原生的通过反射new出来的对象
    private Object originalInstance;

    private Class<?> wrappedClass;


    public BeanWrapper(Object instance) {
        //动态代理的对象
        this.wrappedInstance = aopProxy.getProxy(instance);
        this.originalInstance = instance;
    }

    public Object getWrappedInstance() {

        return wrappedInstance;
    }

    //返回代理以后的class
    //$Proxy0
    public Class<?> getWrappedClass() {
        return wrappedClass;
    }



    public void setAopConfig(AopConfig aopConfig) {
        aopProxy.setConfig(aopConfig);
    }
}
