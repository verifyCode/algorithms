package com.biz.spring.demo.annotation;

import java.lang.annotation.*;

/**
 * @author xjn
 * @since 2020-03-08
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
    String value() default "";
}
