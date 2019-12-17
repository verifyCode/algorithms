package com.stack_and_queue;


/**
 * @author xjn
 * @since 2019-12-16
 * <p>
 * front == tail:表示队列为空
 * (tail+1)%c == front 队列满
 */
public class LoopQueue<E> implements Queue<E> {

    /*** 数据 ***/
    private E[] data;
    /*** 队头 ***/
    private int front;
    /*** 队尾 ***/
    private int tail;
    /*** 队列 ***/
    private int size;

    public LoopQueue(int capacity) {
        this.data = (E[]) new Object[capacity + 1];
        this.size = 0;
        this.front = 0;
        this.tail = 0;

    }

    public LoopQueue() {
        this(10);
    }

    @Override
    public int getSize() {
        return this.size;
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public void enQueue(E e) {
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        this.data = newData;
        this.front = 0;
        this.tail = size;
    }

    @Override
    public E deQueue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is Empty");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        if (size == data.length / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }


    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is Empty");
        }
        return data[front];
    }

    @Override
    public boolean isEmpty() {
        return this.front == this.tail;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, data.length));
        res.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LoopQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.enQueue(i);
            System.out.println(queue);
        }
    }
}
