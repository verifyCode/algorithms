package com.biz.producer_cosumer.threadpool;

import com.biz.producer_cosumer.threadpool.queue.RunnableQueue;

/**
 * @author xjn
 * @since 2020-03-04
 */
public class InternalTask implements Runnable {
    private final RunnableQueue runnableQueue;
    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        //如果当前线程在运行中切没有被中断，则不断从缓存队列中拿出线程运行
        while (running && !Thread.currentThread().isInterrupted()){
            try {
                Runnable task = runnableQueue.take();
                task.run();
            } catch (Exception e) {
                running = false;
                break;
            }
        }
    }
    //停止当前任务，会在shutdown中使用
    public void stop(){
        this.running = false;
    }
}
