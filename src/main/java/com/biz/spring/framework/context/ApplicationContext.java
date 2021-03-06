package com.biz.spring.framework.context;

import com.biz.spring.demo.annotation.*;
import com.biz.spring.framework.aop.AopConfig;
import com.biz.spring.framework.beans.BeanDefinition;
import com.biz.spring.framework.beans.BeanPostProcessor;
import com.biz.spring.framework.beans.BeanWrapper;
import com.biz.spring.framework.context.support.BeanDefinitionReader;
import com.biz.spring.framework.core.BeanFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xjn
 * @since 2020-03-09
 */
public class ApplicationContext extends DefaultListableBeanFactory implements BeanFactory {

    private String[] configLocations;

    private BeanDefinitionReader beanDefinitionReader;


    //用来保证注册是单例的容器
    //Key-className
    private Map<String, Object> beanCacheMap = new HashMap<>();

    //存储所有的被代理过的对象
    private Map<String, BeanWrapper> beanWrapperMap = new ConcurrentHashMap<>();

    public ApplicationContext(String... location) {
        this.configLocations = location;
        refresh();
    }

    //依赖注入,从这里开始
    //通过读取BeanDefinition中的信息
    //然后通过反射机制,创建一个实例并返回
    //Spring不会把原始对象放出去,而是用BeanWrapper来进行包装
    //装饰器模式:
    //1. 保留原来的OOP关系
    //2. 我需要对他进行扩展,增强 为了以后的aop打基础
    @Override
    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        String beanClassName = beanDefinition.getBeanClassName();
        try {

            Object instance = instantionBean(beanDefinition);
            if (instance == null) {
                return null;
            }
            //生成通知事件
            BeanPostProcessor beanPostProcessor = new BeanPostProcessor();
            //在实例初始化之前调用一次
            beanPostProcessor.postProcessorBeforeInitialization(instance, beanName);

            BeanWrapper beanWrapper = new BeanWrapper(instance);
            beanWrapper.setPostProcessor(beanPostProcessor);
            //aop未完
            beanWrapper.setAopConfig(instantionAopConfig(beanDefinition));
            this.beanWrapperMap.put(beanName, beanWrapper);
            //在实例初始化之后调用一次
            beanPostProcessor.postProcessorAfterInitialization(instance, beanName);

            //依赖注入 todo 解决依赖初始化
            populateBean(beanName, instance);

            //通过这么调用,相当于给自己留下了可操作的空间
            return this.beanWrapperMap.get(beanName).getWrappedInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void refresh() {
        //定位
        this.beanDefinitionReader = new BeanDefinitionReader(configLocations);

        //加载
        List<String> classNames = beanDefinitionReader.loadBeanDefinitions();

        //注册
        //1. className --> beanDefinition
        //2. beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
        doRegistry(classNames);

        //依赖注入(lazy-init = false 要执行依赖注入)
        //1. getBean();
        //   a:beanWrapperMap.put(beanName, beanWrapper);
        //   b:populateBean()
        doAutowired();
    }

    private void doAutowired() {
        for (Map.Entry<String, BeanDefinition> entry : this.beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            //非延时加载
            if (!beanDefinition.isLazyInit()) {
                getBean(beanName);
            }
        }
    }

    public void populateBean(String beanName, Object instance) {
        Class<?> aClass = instance.getClass();
        if (!aClass.isAnnotationPresent(Service.class) /**|| !aClass.isAnnotationPresent(.class)***/) {
            return;
        }

        Field[] fields = aClass.getFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Autowired.class)) {
                continue;
            }
            Autowired autowired = field.getAnnotation(Autowired.class);
            String autowiredBeanName = autowired.value().trim();
            if ("".equals(autowiredBeanName)) {
                autowiredBeanName = field.getType().getName();
            }
            field.setAccessible(true);
            try {
                //
                field.set(instance, beanWrapperMap.get(autowiredBeanName).getWrappedInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    //真的BeanDefinition注册到IOC容器中
    private void doRegistry(List<String> classNames) {
        try {
            for (String className : classNames) {
                //beanName有是3种情况
                //1. 默认是类名首字母小写
                //2. 自定义名字
                //3. 接口注入
                Class<?> beanClass = Class.forName(className);
                //如果是一个接口,是不能实例化,
                //用它实现类实例化
                if (beanClass.isInterface()) {
                    continue;
                }
                //className --->  BeanDefinition
                BeanDefinition beanDefinition = beanDefinitionReader.registerBean(className);
                if (beanDefinition != null) {
                    //FactoryBeanName就是bean在ioc中的名字
                    this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
                }
                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> a : interfaces) {
                    //如果是多个实现类,只能覆盖
                    //这个时候可以自定义名称
                    this.beanDefinitionMap.put(a.getName(), beanDefinition);
                }
                //到这里为止,容器初始化完毕
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //传入BeanDefinition返回一个实例Bean
    private Object instantionBean(BeanDefinition beanDefinition) {
        Object instance = null;
        String className = beanDefinition.getBeanClassName();
        try {
            Class<?> aClass = Class.forName(className);
            //todo  如果是单例
//            if(beanDefinition.is){
//
//            }
            if (this.beanCacheMap.containsKey(className)) {
                instance = this.beanCacheMap.get(className);
            } else {
                instance = aClass.newInstance();
                beanCacheMap.put(className, instance);
            }
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    public Properties getConfig() {
        return this.beanDefinitionReader.getConfig();
    }

    private AopConfig instantionAopConfig(BeanDefinition beanDefinition) throws Exception {
        AopConfig aopConfig = new AopConfig();
        String expression = beanDefinitionReader.getConfig().getProperty("pointCut");
        String[] before = beanDefinitionReader.getConfig().getProperty("aspectBefore").split("\\s");
        String[] after = beanDefinitionReader.getConfig().getProperty("aspectAfter").split("\\s");

        String beanClassName = beanDefinition.getBeanClassName();
        Class<?> aClass = Class.forName(beanClassName);
        Pattern expressionPattern = Pattern.compile(expression);
        for (Method m : aClass.getMethods()) {
            Matcher matcher = expressionPattern.matcher(m.toString());
            if (matcher.matches()) {
                aopConfig.put(m, aClass.newInstance(), new Method[]{aClass.getMethod(before[1]), aClass.getMethod(after[1])});
            }
        }
        return aopConfig;
    }

}
