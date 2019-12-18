package com.list;

/**
 * @author xjn
 * @since 2019-12-17
 */
public class Main {

    public static void main(String[] args) {
        LinkedList2 linkedList = new LinkedList2();

        for (int i = 0; i < 10; i++) {
            linkedList.addFirst(i);
        }
        System.out.println(linkedList.toString());
        System.out.println(linkedList.remove(2));
        System.out.println(linkedList.toString());
        System.out.println(linkedList.removeFirst());
        System.out.println(linkedList.toString());
    }
}
