package com.spring.demo.test;

import com.spring.demo.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xjn
 * @since 2020-03-08
 */
@Controller
@RequestMapping("/demo")
public class DemoAction {
    @Autowired
    private DemoService demoService;

    @RequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response, @RequestParam("name") String name) {
        String result = demoService.get(name);
        return result;
    }
}
