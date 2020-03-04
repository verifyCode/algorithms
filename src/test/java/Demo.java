/**
 * @author xjn
 * @since 2020-02-29
 */
public class Demo {
    private static Object obj = new Object();

    public static void main(String[] args) {
        Runnable run = () -> {
            synchronized (obj) {
                String name = Thread.currentThread().getName();
                System.out.println(name + "进入同步代码块");
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t1 = new Thread(run);
        t1.setName("t1");
        t1.start();
        Thread t2 = new Thread(run);
        t2.setName("t2");
        t2.start();
        System.out.println("停止线程前");
        t2.interrupt();
        System.out.println("停止线程后");
        System.out.println(t2.getName() + " " + t2.getState());

        System.out.println(t1.getName() + " " + t1.getState());
    }
}
