import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xjn
 * @since 2020-05-22
 */
public class DeadLock2 {

    public static void main1(String[] args) throws Exception {
        ReentrantLock lockA = new ReentrantLock();
        ReentrantLock lockB = new ReentrantLock();

        Runnable runnable1 = () -> {
            lockB.lock();
            try {
                Thread.sleep(100);
            } catch (Exception e) {

            }
            lockA.lock();
            System.out.println("获取锁A..释放锁B");

            lockA.unlock();
            lockB.unlock();
        };
        Runnable runnable2 = () -> {
            lockA.lock();
            lockB.lock();
            try {
                Thread.sleep(100);
            } catch (Exception e) {

            }
            System.out.println("获取锁B..释放锁A");

            lockB.unlock();
            lockA.unlock();
            ;
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
    }

    //A
    //B
    //C
    //AB异步 C在AB执行完成之后执行
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private volatile int flag = 0;

    public void printA() throws Exception {
        System.out.println("A");
        flag++;
    }

    public void printB() throws Exception {
        System.out.println("B");
        flag++;
    }

    public void printC() {
        while (flag != 2) {

        }
        System.out.println("C");
    }

    public static void main(String[] args) {
        DeadLock2 deadLock2 = new DeadLock2();
        Runnable a = () -> {
            try {
                deadLock2.printA();
            } catch (Exception e) {

            }
        };
        Runnable b = () -> {
            try {
                deadLock2.printB();
            } catch (Exception e) {

            }
        };
        Runnable c = () -> {
            try {
                deadLock2.printC();
            } catch (Exception e) {

            }
        };
//        Thread threadA = new Thread(a);
//        Thread threadB = new Thread(b);
//        Thread threadC = new Thread(c);
//        threadA.start();
//        threadB.start();
//        threadC.start();
        CompletableFuture.allOf(CompletableFuture.runAsync(a), CompletableFuture.runAsync(b))
                .thenRun(c);
    }


}
