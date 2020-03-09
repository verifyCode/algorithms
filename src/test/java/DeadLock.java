import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author xjn
 * @since 2020-03-04
 */
public class DeadLock {
    private static Object A = new Object();
    private static Object B = new Object();
    private static ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();

    public static void main(String[] args) throws Exception {
        Runnable a = () -> {
            mxBean.findDeadlockedThreads();
            synchronized (A) {
                System.out.println("获取到A...尝试获取B");
                synchronized (B) {
                    System.out.println("获取到AB");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {

                    }
                }
            }
        };
        Runnable b = () -> {
            synchronized (B) {
                System.out.println("获取到B...尝试获取A");
                synchronized (A) {
                    System.out.println("获取到AB");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {

                    }
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(a).start();
            new Thread(b).start();
        }

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    }


}
