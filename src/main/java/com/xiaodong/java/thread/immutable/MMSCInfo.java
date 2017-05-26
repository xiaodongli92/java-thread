package com.xiaodong.java.thread.immutable;

/**
 * Created by xiaodong on 2017/5/26.
 * 使用不可变对象表示彩信中心信息
 */
public final class MMSCInfo {

    /**
     * 设备ID
     */
    private final String deviceID;

    /**
     * 彩信中心URL
     */
    private final String url;

    /**
     * 该彩信中心允许的最大附件大小
     */
    private final int maxAttachmentSizeInBytes;

    public MMSCInfo(String deviceID, String url, int maxAttachmentSizeInBytes) {
        this.deviceID = deviceID;
        this.url = url;
        this.maxAttachmentSizeInBytes = maxAttachmentSizeInBytes;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public String getUrl() {
        return url;
    }

    public int getMaxAttachmentSizeInBytes() {
        return maxAttachmentSizeInBytes;
    }
}
