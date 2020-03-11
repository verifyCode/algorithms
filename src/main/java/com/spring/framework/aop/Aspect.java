package com.spring.framework.aop;

import java.lang.reflect.Method;

/**
 * @author xjn
 * @since 2020-03-11
 * 对增强代码的封装
 */
public class Aspect {
    private Object aspect;

    private Method[] points;

    public Aspect(Object aspect, Method[] points) {
        this.aspect = aspect;
        this.points = points;
    }

    public Object getAspect() {
        return aspect;
    }


    public Method[] getPoints() {
        return points;
    }

}
