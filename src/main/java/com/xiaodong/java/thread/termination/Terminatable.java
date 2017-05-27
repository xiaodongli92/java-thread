package com.xiaodong.java.thread.termination;

/**
 * Created by xiaodong on 2017/5/27.
 */
public interface Terminatable extends Runnable {

    void terminate();
}
