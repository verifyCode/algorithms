package com.biz.lock;


import java.io.Serializable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author xjn
 * @since 2020-07-05
 * 共享不公平锁
 */
public class ShareLock implements Serializable {
    //同步器
    private final Sync sync;
    //用于确保不能超过最大值
    private final int maxCount;

    public ShareLock(int count) {
        sync = new Sync(count);
        this.maxCount = count;
    }

    public boolean lock(int count) {
        return sync.acquireByShared(count);
    }

    public boolean unLock() {
        return sync.tryReleaseShared(1);
    }

    class Sync extends AbstractQueuedSynchronizer {
        // 表示最多有 count 个共享锁可以获得
        public Sync(int count) {
            setState(count);
        }

        //获得i个锁
        public boolean acquireByShared(int i) {
            //自旋保证CAS一定可以成功
            while (true) {
                if (i < 0) {
                    return false;
                }
                int state = getState();
                //如果没有锁可以获得, 直接返回false
                if (state <= 0) {
                    return false;
                }

                int expectState = state - i;
                if (expectState < 0) {
                    return false;
                }
                //CAS尝试得到锁,CAS成功获得锁,失败继续for循环
                if (compareAndSetState(state, expectState)) {
                    return true;
                }
            }
        }


        @Override
        protected boolean tryReleaseShared(int arg) {
            while (true) {
                if (arg < 0) {
                    return false;
                }
                int state = getState();
                int expectState = state + arg;
                if (expectState < 0 || expectState > maxCount) {
                    return false;
                }
                if (compareAndSetState(state, expectState)) {
                    return false;
                }
            }
        }
    }

    public static void main(String[] args) {
    }
}