package com.list;

/**
 * @author xjn
 * @since 2019-12-17
 */
public class Main {

    public static void main(String[] args) {
        LinkedList3 linkedList = new LinkedList3();

        for (int i = 0; i < 10; i++) {
            linkedList.addFirst(i);

        }
        System.out.println(linkedList);
        linkedList.reverseList();
        System.out.println(linkedList);
    }
}
