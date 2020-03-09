package com.spring.demo.annotation;

import java.lang.annotation.*;

/**
 * @author xjn
 * @since 2020-03-08
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
    String value() default "";
}
