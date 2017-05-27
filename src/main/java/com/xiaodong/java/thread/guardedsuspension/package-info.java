/**
 * Created by xiaodong on 2017/5/27.
 *
 * 保护性暂挂模式
 *
 * 优点：
 * 将线程挂起和唤醒等容易犯错误的技术细节在应用代码中提取出来统一封装
 * 关注点分离：各个参与者仅关心所有解决问题的一个方面
 * 缺点：
 * 可能增加JVM垃圾回收的负担：每次guardedMethod被调用，都会有一个新的GuardedMethod实例被创建
 * 这jvm增加 eden区域内存的占用，从而可能增加垃圾回收的负担
 * 可能增加上下文切换：如果频繁的出现保护方法被调用时b保护条件不成立
 * 保护方法的执行线程就会被频繁的暂挂和唤醒
 *
 * GuardedObject:包含受保护方法的对象
 *      guardedMethod：受保护方法
 *      stateChanged：改变状态的方法.负责在保护条件成立时唤醒受保护方法的执行线程
 * GuardedAction：抽象了目标动作，并关联了目标动作所需的保护条件
 *      call：表示目标动作的方法
 * Predicate：抽象了保护条件
 *      evaluate：用于表示保护条件的方法
 * Blocker：负责对执行保护方法的线程进行挂起和唤醒。
 */
package com.xiaodong.java.thread.guardedsuspension;