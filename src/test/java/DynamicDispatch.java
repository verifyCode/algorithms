/**
 * @author xjn
 * @since 2020-03-08
 */
public class DynamicDispatch {
    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {

        @Override
        protected void sayHello() {
            System.out.println("Man");
        }
    }

    static class Woman extends Human {

        @Override
        protected void sayHello() {
            System.out.println("WoMan");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }
}
