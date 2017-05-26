package com.xiaodong.java.thread.guardedsuspension;

/**
 * Created by xiaodong on 2017/5/26.
 */
public final class AlarmInfo {

    private final String title;

    private final String message;

    public AlarmInfo(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }
}
