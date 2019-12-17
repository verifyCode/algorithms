package com.stack_and_queue;

/**
 * @author xjn
 * @since 2019-12-16
 */
public interface Queue<E> {
    /**
     * 大小
     *
     * @return
     */
    int getSize();

    /**
     * 入队
     *
     * @param e
     */
    void enQueue(E e);

    /**
     * 出队
     *
     * @return
     */
    E deQueue();

    /**
     * 获取对手元素
     *
     * @return
     */
    E getFront();

    boolean isEmpty();
}
