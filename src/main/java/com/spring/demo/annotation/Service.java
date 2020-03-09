package com.spring.demo.annotation;

import java.lang.annotation.*;

/**
 * @author xjn
 * @since 2020-03-09
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {
    String value() default "";

}
