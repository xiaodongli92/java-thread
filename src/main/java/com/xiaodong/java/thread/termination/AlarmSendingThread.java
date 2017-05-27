package com.xiaodong.java.thread.termination;

import com.sun.deploy.util.StringUtils;
import com.xiaodong.java.thread.guardedsuspension.AlarmAgent;
import com.xiaodong.java.thread.guardedsuspension.AlarmInfo;
import com.xiaodong.java.thread.guardedsuspension.AlarmType;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xiaodong on 2017/5/27.
 */
public class AlarmSendingThread extends AbstractTerminatableThread {

    private final AlarmAgent alarmAgent = new AlarmAgent();

    //告警队列
    private final BlockingQueue<AlarmInfo> alarmQueue;
    private final ConcurrentHashMap<String,AtomicInteger> submittedAlarmRegistry;

    public AlarmSendingThread() {
        alarmQueue = new ArrayBlockingQueue<AlarmInfo>(100);
        submittedAlarmRegistry = new ConcurrentHashMap<>();
        alarmAgent.init();
    }


    @Override
    protected void doRun() throws Exception {
        AlarmInfo alarmInfo = alarmQueue.take();
        terminationToken.reservations.decrementAndGet();
        try {
            //将告警邮件发送至告警服务器
            alarmAgent.sendAlarm(alarmInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //处理恢复告警：将相应的故障告警从注册表中删除
        // 使得相应故障恢复后若再次出现相同的故障，该故障信息能够上报到服务器
        if (AlarmType.RESUME == alarmInfo.getType()) {
            String key = makeKey(alarmInfo, AlarmType.FAULT);
            submittedAlarmRegistry.remove(key);
            key = makeKey(alarmInfo);
            submittedAlarmRegistry.remove(key);
        }
    }

    public int sendAlarm(final AlarmInfo alarmInfo) {
        if (terminationToken.isToShutdown()) {
            return -1;
        }
        AlarmType alarmType = alarmInfo.getType();
        String title = alarmInfo.getTitle();
        String message = alarmInfo.getMessage();
        int duplicateSubmissionCount = 0;
        try {
            AtomicInteger prevSubmittedCounter;

            prevSubmittedCounter = submittedAlarmRegistry.putIfAbsent(makeKey(alarmInfo),
                    new AtomicInteger(0));
            if (null == prevSubmittedCounter) {
                terminationToken.reservations.incrementAndGet();
                alarmQueue.put(alarmInfo);
            } else {
                //故障未恢复，不用重新发送告警信息给给服务器，故仅增加计数
                duplicateSubmissionCount = prevSubmittedCounter.incrementAndGet();
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return duplicateSubmissionCount;
    }

    @Override
    protected void doCleanUp(Exception e) {
        if (null != e && !(e instanceof InterruptedException)) {
            e.printStackTrace();
        }
        alarmAgent.disconnect();
    }

    private static String makeKey(AlarmInfo alarmInfo) {
        return makeKey(alarmInfo, alarmInfo.getType());
    }

    private static String makeKey(AlarmInfo alarmInfo, AlarmType alarmType) {
        return alarmType.getValue() + ':' + alarmInfo.getTitle()
                + "@" + alarmInfo.getMessage();
    }
}
