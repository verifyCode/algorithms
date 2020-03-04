package com.producer_cosumer;

/**
 * @author xjn
 * @since 2020-03-02
 */
public class ProducerConsumerWithWaitNotify {
    class Resource {
        //当前资源数量
        private int num = 0;
        //资源中允许最大存放的资源数目
        private int size = 10;

        public synchronized void remove() {
            if (num > 0) {
                num--;
                System.out.println("消费者" + Thread.currentThread().getName() +
                        "消耗一件资源，" + "当前线程池有" + num + "个");
                notifyAll();
            } else {
                try {
                    wait();
                } catch (Exception e) {

                }
            }
        }

        public synchronized void add() {
            if (num < size) {
                num++;
                System.out.println(Thread.currentThread().getName() + "生产一件资源，当前资源池有"
                        + num + "个");
                //通知等待的消费者

            } else {
                //如果当前资源池中有10件资源
                try {
                    wait();//生产者进入等待状态，并释放锁
                    System.out.println(Thread.currentThread().getName() + "线程进入等待");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
