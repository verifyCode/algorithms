/**
 * @author xjn
 * @since 2020-03-02
 */
public class Finally {
    static int value = 0;

    static int inc() {
        return ++value;
    }

    static int dec() {
        return --value;
    }

    static int getResult() {
        try {
            return inc();
        } finally {
            return dec();
        }
    }

    public static void main(String[] args) {
//        System.out.println(getResult());
//        System.out.println(value);
        System.out.println(a());
    }


    static int a() {
        try {
            return 2;
        } finally {
            return 1;
        }
    }
}
