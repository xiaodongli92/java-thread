package com.xiaodong.java.thread.guardedsuspension;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

/**
 * Created by xiaodong on 2017/5/26.
 * 负责链接告警服务器 并发送告警信息到告警服务器
 */
public class AlarmAgent {

    private volatile boolean connectedToServer = false;

    private final Predicate agentConnected = new Predicate() {
        @Override
        public boolean evaluate() {
            return connectedToServer;
        }
    };

    private final Blocker blocker = new ConditionVarBlocker();

    //心跳定时器
    private final Timer heartbeatTimer = new Timer(true);

    public void sendAlarm(final AlarmInfo alarmInfo) throws Exception {
        //可能需要等待，直到AlarmAgent链接上告警服务器（或者链接终端后重新连上服务器）
        GuardedAction<Void> guardedAction = new GuardedAction<Void>(agentConnected) {
            @Override
            public Void call() throws Exception {
                doSendAlarm(alarmInfo);
                return null;
            }
        };
        blocker.callWithGuard(guardedAction);
    }

    private void doSendAlarm(AlarmInfo alarmInfo) {
        //省略其他代码

        //模拟发送至告警服务器
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            ;
        }
    }

    public void init() {
        //省略其他代码

        //告警链接线程
        Thread connectingThread = new Thread(new ConnectingTask());
        connectingThread.start();
        heartbeatTimer.schedule(new HeartbeatTask(), 60000, 2000);
    }

    public void disconnect() {
        //省略其他代码
        connectedToServer = false;
    }

    protected void onConnected() {
        try {
            blocker.signalAfter(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    connectedToServer = true;
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onDisconnected() {
        connectedToServer = false;
    }


    //负责与告警服务器建立网络链接
    private class ConnectingTask implements Runnable {
        @Override
        public void run() {
            //省略链接代码

            //模拟链接操作耗时
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                ;
            }
        }
    }

    /**
     * 心跳定时任务：定时检查与告警服务器的链接是否正常，发现异常后自动重新链接
     */
    private class HeartbeatTask extends TimerTask {
        //省略其他代码

        @Override
        public void run() {
            //省略其他代码

        }

        private boolean testConnection() {
            //省略其他代码
            return true;
        }

        private void reconnect() {
            ConnectingTask connectingTask = new ConnectingTask();
            //直接在心跳定时器线程中执行
            connectingTask.run();
        }
    }
}
