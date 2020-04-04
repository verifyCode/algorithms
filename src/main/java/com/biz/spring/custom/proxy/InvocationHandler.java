package com.biz.spring.custom.proxy;

import java.lang.reflect.Method;

/**
 * @author xjn
 * @since 2020-03-11
 */
public interface InvocationHandler {
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
