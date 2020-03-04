package com.producer_cosumer.threadpool;

/**
 * @author xjn
 * @since 2020-03-04
 */
@FunctionalInterface
public interface ThreadFactory {
    Thread creatThread(Runnable runnable);
}
