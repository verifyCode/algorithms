package com.spring.framework.beans;

import com.spring.framework.core.FactoryBean;

/**
 * @author xjn
 * @since 2020-03-09
 */
public class BeanWrapper extends FactoryBean {

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
        this.wrappedInstance = instance;
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

}
