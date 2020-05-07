package com.biz.immutable;

/**
 * @author xjn
 * @since 2020-05-06
 * 如何理解字符串的不可变
 */
public class StringImmutable {

    public static String appendStr(String s) {
        s += "bbb";
        return s;
    }

    public static StringBuilder appendSb(StringBuilder builder) {
        return builder.append("bbb");
    }

    public StringImmutable() {
    }

    public static void testDemo(Demo demo) {
        demo.test += "!23";
    }

    public static void main(String[] args) {
        String s = "aaa";
        String s1 = appendStr(s);
        System.out.println(s1);
        System.out.println(s);

        Demo demo = new Demo();
        demo.test = "123";
        System.out.println(demo.test);
        testDemo(demo);
        System.out.println(demo.test);
    }


}
