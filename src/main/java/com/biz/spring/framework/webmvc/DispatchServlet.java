package com.biz.spring.framework.webmvc;


import com.biz.spring.demo.annotation.Controller;
import com.biz.spring.demo.annotation.RequestMapping;
import com.biz.spring.demo.annotation.RequestParam;
import com.biz.spring.framework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xjn
 * @since 2020-03-08
 */
//servlet只是作为mvc的启动入口
public class DispatchServlet extends HttpServlet {

    private final String LOCATION = "contextConfigLocation";

    private List<HandlerMapping> handlerMappings = new ArrayList<>();

    private Map<HandlerMapping, HandlerAdapter> handlerAdapterMap = new HashMap<>();

    private List<ViewResolver> viewResolvers = new ArrayList<>();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception, Msg :" + Arrays.toString(e.getStackTrace()));
        }
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationContext context = new ApplicationContext(config.getInitParameter(LOCATION));
        initStrategies(context);
    }

    private HandlerMapping getHandlerMapping(HttpServletRequest req) {
        //循环handlerMapping
        if (handlerMappings.isEmpty()) {
            return null;
        }

        //
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");


        for (HandlerMapping handlerMapping : handlerMappings) {

            Matcher matcher = handlerMapping.getPattern().matcher(url);

            if (!matcher.matches()) {
                continue;
            }

            return handlerMapping;
        }

        return null;

    }

    private HandlerAdapter getHandlerAdapter(HandlerMapping handlerMapping) {
        if (handlerAdapterMap.isEmpty()) {
            return null;
        }
        return handlerAdapterMap.get(handlerMapping);
    }


    private void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //controller.method.pattern
        HandlerMapping handlerMapping = getHandlerMapping(request);
        if (handlerMapping == null) {
            response.getWriter().write("404 Not Found");
            return;
        }
        HandlerAdapter ha = getHandlerAdapter(handlerMapping);
        ModelAndVew mv = ha.handle(request, response, handlerMapping);
        processDispatchResult(response, mv);

    }

    public void processDispatchResult(HttpServletResponse resp, ModelAndVew mv) throws Exception {
        if (null == mv) {
            return;
        }
        if (viewResolvers.isEmpty()) {
            return;
        }
        for (ViewResolver resolver : viewResolvers) {
            if (!mv.getView().equals(resolver.getViewName())) {
                continue;
            }

            String r = resolver.parse(mv);

            if (r != null) {
                resp.getWriter().write(r);
                break;
            }
        }
    }


    private void initStrategies(ApplicationContext context) {
        //文件上传解析-未实现
        initMultipartResolver(context);
        //本地化解析-未实现
        initLocaleResolver(context);
        //主题解析View-未实现
        initThemeResolver(context);

        //请求映射到处理器-重要 -->HandlerMapping(controller,method,pattern)
        initHandlerMappings(context);
        //类型的参数动态匹配-重要 -->HandlerAdapter(Map<String, Integer> paramMapping)
        // this.handlerAdapterMap.put(handlerMapping, new HandlerAdapter(paramMapping));
        initHandlerAdapters(context);
        //如果执行中遇到异常-未实现
        initHandlerExceptionResolvers(context);
        //直接解析请求到视图-未实现
        initRequestToViewNameTranslator(context);
        //解析逻辑视图到具体实现-实现
        //解决一个页面名字和模板文件关联的问题
        initViewResolvers(context);
        //Flash映射管理器-未实现
        initFlashMapManager(context);
    }

    private void initFlashMapManager(ApplicationContext context) {
    }

    private void initRequestToViewNameTranslator(ApplicationContext context) {
    }

    private void initHandlerExceptionResolvers(ApplicationContext context) {
    }

    private void initLocaleResolver(ApplicationContext context) {
    }

    private void initThemeResolver(ApplicationContext context) {
    }

    private void initMultipartResolver(ApplicationContext context) {
    }


    //主要是用来动态匹配参数的
    private void initHandlerAdapters(ApplicationContext applicationContext) {
        //在初始化阶段,将参数的名字或者类型,按照一定的顺序保存下来.
        //因为后面用反射调用的时候,传的形参是一个数组
        //可以通过记录这些参数的位置,挨个从数组中填值,这样的话就和参数的顺序无关了;

        for (HandlerMapping handlerMapping : handlerMappings) {
            //每一个方法有一个形参列表,那么这里保存的就是形参列表
            Map<String, Integer> paramMapping = new HashMap<>();
            Annotation[][] parameterAnnotations = handlerMapping.getMethod().getParameterAnnotations();

            //这里只是处理了命名参数
            for (int i = 0; i < parameterAnnotations.length; i++) {
                for (Annotation a : parameterAnnotations[i]) {
                    if (a instanceof RequestParam) {
                        String paramName = ((RequestParam) a).value();
                        if (!Objects.equals(paramName.trim(), "")) {
                            paramMapping.put(paramName, i);
                        }
                    }
                }
            }
            //处理非命名参数,只处理Request和Response
            Class<?>[] parameterTypes = handlerMapping.getMethod().getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                Class<?> type = parameterTypes[i];
                if (type == HttpServletRequest.class || type == HttpServletResponse.class) {
                    paramMapping.put(type.getName(), i);
                }
            }
            this.handlerAdapterMap.put(handlerMapping, new HandlerAdapter(paramMapping));

        }
    }

    private void initViewResolvers(ApplicationContext applicationContext) {
        //http://localhost:8080/first.html
        //解决一个页面名字和模板文件关联的问题
        String templateRoot = applicationContext.getConfig().getProperty("templateRoot");
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();

        File templateRootDir = new File(templateRootPath);
        for (File template : templateRootDir.listFiles()) {
            this.viewResolvers.add(new ViewResolver(template.getName(), template));
        }
    }


    //将Controller中配置的RequestMapping和Method进行一一对应
    public void initHandlerMappings(ApplicationContext applicationContext) {
        //Map<String,Method> map;
        //Map<Url,Method>
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

        //首先从容器中获取所有实例
        for (String beanName : beanDefinitionNames) {
            Object controller = applicationContext.getBean(beanName);

            Class<?> aClass = controller.getClass();
            if (!aClass.isAnnotationPresent(Controller.class)) {
                continue;
            }
            String baseUrl = "";
            if (aClass.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping annotation = aClass.getAnnotation(RequestMapping.class);
                baseUrl = annotation.value();
            }
            //扫描所有的Method方法
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(RequestMapping.class)) {
                    continue;
                }
                RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                String regex = ("/" + baseUrl + annotation.value().replaceAll("/+", "/"));
                Pattern pattern = Pattern.compile(regex);
                this.handlerMappings.add(new HandlerMapping(controller, method, pattern));
            }
        }
    }

}


