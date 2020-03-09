/**
 * @author xjn
 * @since 2020-03-08
 */
public class StopTest {
    public static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            MyThread3 t = new MyThread3(true);
            t.start();
        }
        Thread.sleep(1000);
        flag = false;
//  t.stop();
    }
}

class MyThread3 extends Thread {

    public MyThread3(boolean flag) {

    }

    @Override
    public void run() {
        while (StopTest.flag) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("hello");
        }
    }

}
