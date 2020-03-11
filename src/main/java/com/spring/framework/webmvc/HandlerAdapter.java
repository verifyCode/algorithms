package com.spring.framework.webmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

/**
 * @author xjn
 * @since 2020-03-10
 */
public class HandlerAdapter {

    private Map<String, Integer> paramMapping;

    public HandlerAdapter(Map<String, Integer> paramMapping) {
        this.paramMapping = paramMapping;
    }

    //主要目的是用反射调用url对应的method
    public ModelAndVew handle(HttpServletRequest request, HttpServletResponse response, HandlerMapping handlerMapping) throws Exception {
        //获取方法上的参数
        Class<?>[] paramTypes = handlerMapping.getMethod().getParameterTypes();
        //要想给参数赋值，只能通过索引号来找到具体的某个参数
        Object[] paramValues = new Object[paramTypes.length];
        Map<String, String[]> params = request.getParameterMap();
        for (Map.Entry<String, String[]> param : params.entrySet()) {
            String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll(",\\s", ",");

            if (!this.paramMapping.containsKey(param.getKey())) {
                continue;
            }
            int index = this.paramMapping.get(param.getKey());

            //单个赋值是不行的
            paramValues[index] = castStringValue(value, paramTypes[index]);
        }
        //request 和 response 要赋值
        String reqName = HttpServletRequest.class.getName();
        if (this.paramMapping.containsKey(reqName)) {
            int reqIndex = this.paramMapping.get(reqName);
            paramValues[reqIndex] = request;
        }
        String resqName = HttpServletResponse.class.getName();
        if (this.paramMapping.containsKey(resqName)) {
            int respIndex = this.paramMapping.get(resqName);
            paramValues[respIndex] = response;
        }
        boolean isModelAndView = handlerMapping.getMethod().getReturnType() == ModelAndVew.class;
        Object r = handlerMapping.getMethod().invoke(handlerMapping.getController(), paramValues);
        if (isModelAndView) {
            return (ModelAndVew) r;
        } else {
            return null;
        }
    }

    private Object castStringValue(String value, Class<?> clazz) {
        if (clazz == String.class) {
            return value;
        } else if (clazz == Integer.class) {
            return Integer.valueOf(value);
        } else if (clazz == int.class) {
            return Integer.valueOf(value).intValue();
        } else {
            return null;
        }
    }
}
