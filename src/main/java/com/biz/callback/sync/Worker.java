package com.biz.callback.sync;

/**
 * @author xjn
 * @since 2020-04-04
 */
public class Worker implements DoHomeWork {

    @Override
    public void doHomeWork(String question, String answer) {
        System.out.println("作业本");
        if(answer != null) {
            System.out.println("作业："+question+" 答案："+ answer);
        } else {
            System.out.println("作业："+question+" 答案："+ "(空白)");
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        String question = "1+1=?";

        new RoomMate().getAnswer(question, worker);

    }


}
