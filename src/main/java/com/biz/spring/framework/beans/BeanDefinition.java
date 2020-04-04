package com.biz.spring.framework.beans;

/**
 * @author xjn
 * @since 2020-03-09
 * 存储配置文件中的信息,保存着内存中的配置
 */
public class BeanDefinition {

    private String beanClassName;

    private boolean lazyInit;

    //FactoryBeanName就是bean在ioc中的名字
    private String factoryBeanName;

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }
}
