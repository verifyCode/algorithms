package com.producer_cosumer.threadpool.exp;

/**
 * @author xjn
 * @since 2020-03-04
 */
public class RunnableDenyException extends RuntimeException {
    public RunnableDenyException(String msg){
        super(msg);
    }
}
