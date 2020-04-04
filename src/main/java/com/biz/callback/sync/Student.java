package com.biz.callback.sync;

/**
 * @author xjn
 * @since 2020-04-04
 */
public class Student {
    public void doHomeWork(String homeWork, String answer) {
        System.out.println("作业本");
        if(answer != null) {
            System.out.println("作业："+homeWork+" 答案："+ answer);
        } else {
            System.out.println("作业："+homeWork+" 答案："+ "(空白)");
        }

    }
}
