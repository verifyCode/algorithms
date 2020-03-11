package com.spring.framework.context;

/**
 * @author xjn
 * @since 2020-03-11
 */
public abstract class AbstractApplicationContext {

    //提供给子类
    protected void onRefresh() {


    }

    protected abstract void refreshBeanFactory();
}
