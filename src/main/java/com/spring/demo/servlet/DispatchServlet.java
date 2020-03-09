package com.spring.demo.servlet;


import com.spring.demo.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xjn
 * @since 2020-03-08
 */
public class DispatchServlet extends HttpServlet {

    private Properties contextConfig = new Properties();

    private Map<String, Object> beanMap = new ConcurrentHashMap<>();

    private List<String> classNames = new ArrayList<>();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //开始初始化的进程

        //定位 字符串来源于配置文件web.xml contextConfigLocation
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        //加载
        doScanner(contextConfig.getProperty("basePackage"));

        //注册
        doRegistry();

        //自动依赖注入
        //在Spring中调用getBean触发依赖注入
        doAutowired();

        //如果是SpringMVC会多一个HandlerMapping
        //将url和RequestMethod关联,起到路由的作用
        initHandlerMapping();
    }

    private void initHandlerMapping() {

    }

    private void doAutowired() {
        if (beanMap.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
            Field[] declaredFields = entry.getValue().getClass().getDeclaredFields();

            for (Field field : declaredFields) {
                if (!field.isAnnotationPresent(Autowired.class)) {
                    continue;
                }
                Autowired annotation = field.getAnnotation(Autowired.class);
                String beanName = annotation.value().trim();
                if ("".equals(beanName)) {
                    beanName = field.getType().getName();
                }
                field.setAccessible(true);
                try {
                    //注入值
                    field.set(entry.getValue(), beanMap.get(beanName));
                } catch (Exception e) {

                }
            }
        }
    }

    private void doRegistry() {
        if (classNames.isEmpty()) {
            return;
        }
        try {
            for (String className : classNames) {
                //在Spring中用的多个子方法来处理
                //parseArray,parseMap
                Class<?> aClass = Class.forName(className);
                if (aClass.isAnnotationPresent(Controller.class)) {
                    String beanName = lowFirstCase(aClass.getSimpleName());

                    //在Spring中这个阶段是不会put instance的,这里put的是BeanDefinition
                    beanMap.put(beanName, aClass.newInstance());
                } else if (aClass.isAnnotationPresent(Service.class)) {
                    Service service = aClass.getAnnotation(Service.class);
                    //默认用类名首字母注入
                    //如果自定义了beanName 那么优先使用自己定义的beanName
                    //如果是一个接口,使用接口的类型去注入

                    //在Spring中会分别使用不同的方法 autowriedByName,autowriedByType
                    String beanName = service.value();
                    if ("".equals(beanName.trim())) {
                        beanName = lowFirstCase(aClass.getSimpleName());
                    }
                    beanMap.put(beanName, aClass.newInstance());
                    Class<?>[] interfaces = aClass.getInterfaces();
                    for (Class clz : interfaces) {
                        beanMap.put(clz.getName(), aClass.newInstance());
                    }
                } else {
                    continue;
                }
            }
        } catch (Exception e) {

        }
    }

    private void doScanner(String basePackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + basePackage.replaceAll("\\.", "/"));
        File classDir = new File(url.getFile());
        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(basePackage + "." + file.getName());
            } else {
                classNames.add(basePackage + "." + file.getName().replace(".class", ""));
            }
        }
    }

    private void doLoadConfig(String contextConfigLocation) {
        //在Spring中是通过Reader去查找定位
        //contextConfigLocation需要处理classPath字符串
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            contextConfig.load(is);
        } catch (IOException e) {

        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (Exception e) {

                }
            }
        }
    }

    //首字母小写-待实现
    private String lowFirstCase(String name) {
        return name;
    }
}
