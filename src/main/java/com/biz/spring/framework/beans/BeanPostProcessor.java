package com.biz.spring.framework.beans;

/**
 * @author xjn
 * @since 2020-03-09
 */
//用于做事件监听
public class BeanPostProcessor {

    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }


    public Object postProcessorAfterInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }
}
