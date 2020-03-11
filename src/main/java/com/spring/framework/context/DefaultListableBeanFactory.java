package com.spring.framework.context;

import com.spring.framework.beans.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xjn
 * @since 2020-03-11
 */
public class DefaultListableBeanFactory extends AbstractApplicationContext{

    //beanDefinitionMap 保存配置信息
    protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    protected void refreshBeanFactory() {

    }

    @Override
    protected void onRefresh() {
        super.onRefresh();
    }


}
