package com.xiaodong.java.thread.guardedsuspension;

import java.util.concurrent.Callable;

/**
 * Created by xiaodong on 2017/5/26.
 */
public abstract class GuardedAction<V> implements Callable<V> {

    protected final Predicate guard;

    public GuardedAction(Predicate guard) {
        this.guard = guard;
    }
}
