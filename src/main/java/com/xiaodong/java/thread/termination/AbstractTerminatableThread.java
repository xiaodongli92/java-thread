package com.xiaodong.java.thread.termination;

/**
 * Created by xiaodong on 2017/5/27.
 *
 * 可停止的抽象类
 */
public abstract class AbstractTerminatableThread extends Thread implements Terminatable {

    public final TerminationToken terminationToken;

    public AbstractTerminatableThread() {
        this(new TerminationToken());
    }

    public AbstractTerminatableThread(TerminationToken terminationToken) {
        super();
        this.terminationToken = terminationToken;
        terminationToken.register(this);
    }

    /**
     * 留给子类实现其线程处理逻辑
     * @throws Exception
     */
    protected abstract void doRun() throws Exception;

    /**
     * 留给子类实现。用于执行线程停止后的一些清理动作
     * @param e
     */
    protected void doCleanUp(Exception e) {

    }

    /**
     * 留给子类实现。用于执行线程停止所需的操作
     */
    protected void doTerminate() {

    }

    @Override
    public void run() {
        Exception e = null;
        try {
            for (;;) {
                //在执行线程的处理逻辑前x先判断线程停止的标志
                if (terminationToken.isToShutdown() && terminationToken.reservations.get()<=0) {
                    break;
                }
                doRun();
            }
        } catch (Exception ex) {
            //使线程能够响应interrupt调用而退出
            e = ex;
        } finally {
            try {
                doCleanUp(e);
            } finally {
                terminationToken.notifyThreadTermination(this);
            }
        }
    }

    @Override
    public void interrupt() {
        terminate();
    }

    @Override
    public void terminate() {
        terminationToken.setToShutdown(true);
        try {
            doTerminate();
        } finally {
            //若无待处理的任务，则试图强制终止线程
            if (terminationToken.reservations.get() < 1) {
                super.interrupt();
            }
        }
    }

    public void terminate(boolean waitUntilThreadTerminate) {
        terminate();
        if (waitUntilThreadTerminate) {
            try {
                this.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
