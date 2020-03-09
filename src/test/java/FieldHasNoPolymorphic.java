/**
 * @author xjn
 * @since 2020-03-08
 */
public class FieldHasNoPolymorphic {
    static class Father {
        public int money = 1;

        public Father() {
            money = 2;
            showMeTheMoney();
        }

        public void showMeTheMoney() {
            System.out.println("I am Father,I have: " + money);
        }
    }

    static class Son extends Father {
        public int money = 3;

        public Son() {
            money = 4;
            showMeTheMoney();
        }

        public void showMeTheMoney() {
            System.out.println("I am son,I have: " + money);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("进入睡眠");
        Thread.sleep(1000);
        System.out.println("醒来");
    }

}
