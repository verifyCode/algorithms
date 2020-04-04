package com.biz.spring.demo.test;


import com.biz.spring.demo.annotation.Service;

/**
 * @author xjn
 * @since 2020-03-08
 */
@Service
public class DemoService {
    public String get(String name) {
        return "name";
    }
}
