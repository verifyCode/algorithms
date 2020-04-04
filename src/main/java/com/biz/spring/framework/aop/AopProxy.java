package com.biz.spring.framework.aop;

/**
 * @author xjn
 * @since 2020-03-11
 */
public interface AopProxy {

    Object getProxy(Object instance);

    void setConfig(AopConfig aopConfig);
}
