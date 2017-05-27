package com.xiaodong.java.thread.termination;

import com.xiaodong.java.thread.guardedsuspension.AlarmInfo;
import com.xiaodong.java.thread.guardedsuspension.AlarmType;

/**
 * Created by xiaodong on 2017/5/27.
 * 报警功能入口类
 */
public class AlarmMgr {

    private static final AlarmMgr instance = new AlarmMgr();

    private volatile boolean shutdownRequested = false;

    private final AlarmSendingThread alarmSendingThread;

    private AlarmMgr() {
        alarmSendingThread = new AlarmSendingThread();
    }

    public static AlarmMgr getInstance() {
        return instance;
    }

    public int sendAlarm(AlarmType alarmType, String title, String message) {
        int duplicateSubmissionCount = 0;
        try {
            AlarmInfo alarmInfo = new AlarmInfo(title, message, alarmType);
            duplicateSubmissionCount = alarmSendingThread.sendAlarm(alarmInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return duplicateSubmissionCount;
    }

    public void init() {
        alarmSendingThread.start();
    }
    public synchronized void shutdown() {
        if (shutdownRequested) {
            throw new IllegalStateException("shutdown already requested");
        }
        alarmSendingThread.terminate();
        shutdownRequested = true;
    }
}
