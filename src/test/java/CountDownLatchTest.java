import java.util.concurrent.*;

/**
 * @author xjn
 * @since 2020-03-02
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws Exception {
//        test1();
        test2();

    }

    public static void test1() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(new Double(Math.random() * 3000).longValue());
                    System.out.println(Thread.currentThread().getName() + "  :准备就绪");
                    countDownLatch.countDown();
                } catch (Exception e) {

                }
            }).start();
        }
        countDownLatch.await();
        System.out.println("游戏开始");
    }

    public static void test2() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(new Double(Math.random() * 3000).longValue());
                    System.out.println(Thread.currentThread().getName() + "  :准备就绪");
                    cyclicBarrier.await();
                } catch (Exception e) {

                }
            }).start();
        }
        System.out.println("游戏开始");

    }

    public static void test3() {
        Phaser phaser = new Phaser();
    }
}
