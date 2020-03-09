package com.spring.framework.webmvc;


import com.spring.framework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xjn
 * @since 2020-03-08
 */
//servlet只是作为mvc的启动入口
public class DispatchServlet extends HttpServlet {

    private final String LOCATION = "contextConfigLocation";


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationContext context = new ApplicationContext(config.getInitParameter(LOCATION));
    }

    private void initHandlerMapping() {

    }


}
