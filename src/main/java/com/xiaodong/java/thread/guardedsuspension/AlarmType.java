package com.xiaodong.java.thread.guardedsuspension;

/**
 * Created by xiaodong on 2017/5/27.
 */
public enum AlarmType {
    RESUME("resume"),//继续
    FAULT("fault"),//过错
    ;

    private String value;

    AlarmType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }
}
