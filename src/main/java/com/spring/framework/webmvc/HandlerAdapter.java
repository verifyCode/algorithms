package com.spring.framework.webmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    public ModelAndVew handle(HttpServletRequest request, HttpServletResponse response, HandlerMapping handlerMapping) {
        return null;
    }
}
