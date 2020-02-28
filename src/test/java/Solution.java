import com.sort.SortTestHelper;

/**
 * @author xjn
 * @since 2019-12-17
 */
public class Solution {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        int[] ints = SortTestHelper.generateRandomArray(10, 1, 10);

        for (int i = 1; i < ints.length; i++) {
            for (int j = 0; j < ints.length - i; j++) {
                System.out.println("i:" + i + " j:" + j);
            }
            System.out.println();
        }

    }
}
