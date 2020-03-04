import org.openjdk.jol.info.ClassLayout;

/**
 * @author xjn
 * @since 2020-02-29
 */
public class Demo2 {

    private static Object object = new Object();

    public static void main(String[] args) throws Exception {
        System.out.println(Integer.toHexString(object.hashCode()));
        System.out.println(ClassLayout.parseInstance(object).toPrintable() + " ******");
        new Thread(() -> {
            synchronized (object) {
                System.out.println(ClassLayout.parseInstance(object).toPrintable() + "++++");
            }
        }).start();
        System.out.println(Integer.toHexString(object.hashCode()));
        System.out.println(ClassLayout.parseInstance(object).toPrintable() + "--------");
    }

}
