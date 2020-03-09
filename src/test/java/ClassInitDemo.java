import sun.misc.Unsafe;

/**
 * @author xjn
 * @since 2020-03-02
 */
public class ClassInitDemo {
    final String result;
    private static final char value[] = new char[10];
    private static ThreadLocal threadLocal;

    public ClassInitDemo(String x, String y) {
        this.result = add(x, y);
    }

    public String add(String x, String y) {
        threadLocal.remove();
        return x + y;
    }

    static class SubClass extends ClassInitDemo {
        String z;

        public SubClass(String x, String y, String z) {
            super(x, y);
            this.z = z;
        }

        public String add(String x, String y) {
            return super.add(x, y) + z;
        }
    }

    public static String appendStr(String s) {
        s += "bbb";
        System.out.println(s);
        return s;
    }

    public static Integer test(Integer a) {
        a += 1;
        return a;
    }

    public static void main(String[] args)throws Exception {
//        System.out.println(new SubClass("A", "B", "C").result);
//        String s = new String("aaa");
//        String ns = appendStr(s);
//        System.out.println("s:" + s.toString());
        Unsafe unsafe = Unsafe.getUnsafe();
        Integer a = 1;
        Integer test = test(a);
        System.out.println("a:" + a);
        System.out.println("test:" + test);
    }
}
