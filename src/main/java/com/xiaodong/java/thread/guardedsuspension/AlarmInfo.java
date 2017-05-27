package com.xiaodong.java.thread.guardedsuspension;

/**
 * Created by xiaodong on 2017/5/26.
 */
public final class AlarmInfo {

    private final String title;

    private final String message;

    private final AlarmType type;

    public AlarmInfo(String title, String message, AlarmType type) {
        this.title = title;
        this.message = message;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public AlarmType getType() {
        return type;
    }
}
