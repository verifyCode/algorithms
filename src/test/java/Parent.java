/**
 * @author xjn
 * @since 2020-03-02
 */
public class Parent {
    public String name = "Parent";

    public static void main(String[] args) {
        System.out.println(new Child().name);
    }

}

class Child extends Parent {
    public String name = "Child";
}
