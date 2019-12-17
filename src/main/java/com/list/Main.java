package com.list;

/**
 * @author xjn
 * @since 2019-12-17
 */
public class Main {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();

        for (int i = 0; i < 10; i++) {
            linkedList.addFirst(i);
        }
        linkedList.addFirst(2);
        System.out.println(linkedList.toString());
        linkedList.removeElement(2);
        System.out.println(linkedList.toString());
    }
}
