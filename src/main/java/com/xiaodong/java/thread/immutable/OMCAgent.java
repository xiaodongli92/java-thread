package com.xiaodong.java.thread.immutable;

/**
 * Created by xiaodong on 2017/5/26.
 * 与运维中心对接的类
 */
public class OMCAgent extends Thread {

    @Override
    public void run() {
        boolean isTableModificationMsg = false;
        String updateedTableName = null;
        while (true) {
            //省略其他代码

            /**
             * 从与OMC链接的Socket中读取消息并进行解析
             * 解析到数据表更新消息后，重置MMSRouter实例
             */
            if (isTableModificationMsg) {
                if ("MMSCInfo".equals(updateedTableName)) {
                    MMSCRouter.setInstance(new MMSCRouter());
                }
            }
            //省略其他代码
        }
    }
}
