/**
 * Created by xiaodong on 2017/5/26.
 * Immutable Object 不可变对象模式
 * 在不使用锁的情况下，既能保证共享变量访问的线程安全，有能避免引入锁可能带来的问题和开销
 *
 *
 * MMSCInfo是严格意义上的不可变对象
 * MMSCRouter可以视作一个等效的不可变对象，这是因为setInstance方法仅仅改变instance指向的对象
 * 而instance变量采用了volatile修饰保证了其在多线程的内存可见性
 * 所以setInstance方法对instance变量的改变无需加锁也能保证线程安全
 * 而且获取路由信息也无须加锁
 *
 * 不可变对象具有天生的线程安全性，避免显式锁等并发访问控制的开销和问题
 *
 * 适用的场景：
 * 1、被建模对象的状态变化不频繁
 * 2、同时对一组相关的数据进行写操作，因此需要保证原子性
 * 3、适用某个对象作为安全的HashMap的Key
 *
 * 典型应用场景 CopyOnWriteArrayList
 * 在集合中添加一个元素的时候，会生成一个新的数组，并将集合中的所有元素复制到新的数组，
 * 然后将新的数组的最后一个元素设置成为要添加的元素
 * 这个新的数组会被直接赋值给array
 */
package com.xiaodong.java.thread.immutable;