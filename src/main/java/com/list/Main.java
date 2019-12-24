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
//        linkedList.addFirst(2);
//        linkedList.addFirst(2);
        System.out.println(linkedList);
        linkedList.removeElement(9);
//        System.out.println(linkedList.get(12));
//        System.out.println(linkedList.contains(2));
//        linkedList.set(1,1);
//        linkedList.set(1,2);
//        linkedList.remove(1);
//        linkedList.add(1,1);
//        linkedList.add(2,1);
        System.out.println(linkedList);
    }
}
