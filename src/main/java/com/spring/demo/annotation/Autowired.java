package com.spring.demo.annotation;

import java.lang.annotation.*;

/**
 * @author xjn
 * @since 2020-03-08
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    String value() default "";
}