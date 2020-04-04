package com.biz.callback.sync;

/**
 * @author xjn
 * @since 2020-04-04
 */
public class RoomMate {
    public void getAnswer(String homework, Student student) {
        if ("1+1=?".equals(homework)) {
            student.doHomeWork(homework, "2");
        } else {
            student.doHomeWork(homework, "(空白)");
        }
    }

    public void getAnswer(String homework, DoHomeWork someone) {
        if ("1+1=?".equals(homework)) {
            someone.doHomeWork(homework, "2");
        } else {
            someone.doHomeWork(homework, "(空白)");
        }
    }

}
