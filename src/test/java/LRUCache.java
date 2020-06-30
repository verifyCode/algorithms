import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author xjn
 * @since 2020-06-29
 */
public class LRUCache {

    class DoubleLinkedList {
        int key;
        int value;
        DoubleLinkedList next;
        DoubleLinkedList prev;

        public DoubleLinkedList(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public DoubleLinkedList() {
        }
    }

    private int size;
    private int capacity;
    private DoubleLinkedList head;
    private DoubleLinkedList tail;
    private Map<Integer, DoubleLinkedList> cache = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.head = new DoubleLinkedList();
        this.tail = new DoubleLinkedList();
        head.next = tail;
        tail.prev = head;
    }

    private void addToHead(DoubleLinkedList node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DoubleLinkedList node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DoubleLinkedList node) {
        removeNode(node);
        addToHead(node);
    }

    private DoubleLinkedList removeTail() {
        DoubleLinkedList node = this.tail.prev;
        removeNode(node);
        return node;
    }


    public int get(int key) {
        DoubleLinkedList node = cache.get(key);
        if (Objects.isNull(node)) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DoubleLinkedList node = this.cache.get(key);
        if (Objects.isNull(node)) {
            node = new DoubleLinkedList(key, value);
            size++;
            moveToHead(node);
            if (this.size > capacity) {
                removeTail();
                size--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }
}
