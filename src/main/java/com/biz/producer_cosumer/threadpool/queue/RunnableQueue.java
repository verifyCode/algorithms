package com.biz.producer_cosumer.threadpool.queue;

/**
 * @author xjn
 * @since 2020-03-04
 * 缓存提交到线程池的队列任务
 */
public interface RunnableQueue {
    //新线程进来时,提交任务到缓存队列
    void offer(Runnable runnable);
    //取出线程
    Runnable take();
    //获取队列中线程的数量
    int size();
}
