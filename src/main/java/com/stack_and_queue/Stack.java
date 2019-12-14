package com.stack_and_queue;

/**
 * @author xjn
 * @since 2019-12-07
 */
public interface Stack<E> {

    /*** 向栈中添加元素 ***/
    void push(E e);

    /*** 弹出栈顶元素 ***/
    E pop();

    /*** 查看栈顶元素 ***/
    E peek();

    int getSize();

    boolean isEmpty();
}
