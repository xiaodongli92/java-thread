package com.xiaodong.java.thread.guardedsuspension;

import java.util.concurrent.Callable;

/**
 * Created by xiaodong on 2017/5/26.
 * 负责对执行guardedAction方法的线程进行挂起和唤醒，并执行ConcreteGuardedAction所实现的目标操作
 */
public interface Blocker {

    /**
     * 在保护条件成立时执行目标动作，否则阻塞当前线程，直到保护条件成立
     * @param guardedAction 带保护条件的目标动作
     * @param <V>
     * @return
     * @throws Exception
     */
    <V> V callWithGuard(GuardedAction<V> guardedAction) throws Exception;

    /**
     * 执行stateOperation所指定的操作后，决定是否唤醒本Blocker所暂挂的所有线程中的一个线程
     * @param stateOperation  更改状态的操作，其call方法返回值为true时，该方法才会唤醒被暂挂的线程
     * @throws Exception
     */
    void signalAfter(Callable<Boolean> stateOperation) throws Exception;

    void signal() throws InterruptedException;

    /**
     * 执行stateOperation所指定的操作后，决定是否唤醒本Blocker所暂挂的所有线程
     * @param stateOperation 更改状态的操作，其call方法的返回值是true时，该方法才会唤醒被暂挂的线程
     * @throws Exception
     */
    void broadcastAfter(Callable<Boolean> stateOperation) throws Exception;
}
