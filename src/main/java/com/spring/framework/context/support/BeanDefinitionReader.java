package com.spring.framework.context.support;

import com.spring.framework.beans.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author xjn
 * @since 2020-03-09
 * <p>
 * 用来对配置文件进行查找,定位,读取,解析
 */
public class BeanDefinitionReader {
    private Properties config = new Properties();

    //扫描的包的集合
    private List<String> registerBeanClasses = new ArrayList<>();

    private final String BASE_PACKAGE = "basePackage";

    public BeanDefinitionReader(String... locations) {
        //在Spring中是通过Reader去查找定位
        //contextConfigLocation需要处理classPath字符串
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classPath:", ""));
        try {
            config.load(is);
        } catch (IOException e) {

        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (Exception e) {

                }
            }
        }

        //扫描
        doScanner(config.getProperty(BASE_PACKAGE));
    }

    public List<String> loadBeanDefinitions() {
        return this.registerBeanClasses;
    }


    //className --->  BeanDefinition
    public BeanDefinition registerBean(String className) {
        if (this.registerBeanClasses.contains(className)) {
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setBeanClassName(className);
            String factoryBeanName = lowFirstCase(className);
            //FactoryBeanName就是bean在ioc中的名字
            beanDefinition.setFactoryBeanName(factoryBeanName);
            return beanDefinition;
        }
        return null;
    }

    public Properties getConfig() {
        return this.config;
    }

    private void doScanner(String basePackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + basePackage.replaceAll("\\.", "/"));
        File classDir = new File(url.getFile());
        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(basePackage + "." + file.getName());
            } else {
                registerBeanClasses.add(basePackage + "." + file.getName().replace(".class", ""));
            }
        }
    }

    //首字母小写-待实现
    private String lowFirstCase(String name) {
        return name;
    }

}
