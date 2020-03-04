/**
 * @author xjn
 * @since 2020-03-02
 */
public class ClassInitDemo {
    final String result;

    public ClassInitDemo(String x, String y) {
        this.result = add(x, y);
    }

    public String add(String x, String y) {
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

    public static void main(String[] args) {
        System.out.println(new SubClass("A", "B", "C").result);
    }
}
