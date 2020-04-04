package com.biz.spring.framework.aop;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xjn
 * @since 2020-03-11
 * 只是对application中的expression的封装
 * 目标代理对象的一个方法要增强
 * 由用户自己实现的业务逻辑去增强
 * 配置文件的目的,告诉Spring,哪些类的方法需要增强,增强的内容是什么
 * 对配置文件中的内容进行封装
 */
public class AopConfig {

    //以目标对象的Method作为key,把需要增强的代码内容作为value
    private Map<Method, Aspect> points = new HashMap<>();

    public void put(Method target, Object aspect, Method[] points) {
        this.points.put(target, new Aspect(aspect, points));
    }

    public Aspect get(Method method) {
        return this.points.get(method);
    }


    public boolean contains(Method method) {
        return this.points.containsKey(method);
    }

}
