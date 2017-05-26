package com.xiaodong.java.thread.immutable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaodong on 2017/5/26.
 * 彩信中心路由管理器
 */
public final class MMSCRouter {

    private static volatile MMSCRouter instance = new MMSCRouter();

    private final Map<String, MMSCInfo> routeMap;

    public MMSCRouter() {
        this.routeMap = retrieveRouteMapFromDB();
    }

    /**
     * 将数据库中数据加载到内存中
     * @return
     */
    private static Map<String, MMSCInfo> retrieveRouteMapFromDB() {
        Map<String,MMSCInfo> map = new HashMap<>();
        //***
        return map;
    }

    /**
     * 根据手机号前缀获取对应彩信中心信息
     * @param mobilePrefix
     * @return
     */
    public MMSCInfo getInfo(String mobilePrefix) {
        return routeMap.get(mobilePrefix);
    }

    /**
     * 将当前的实例更新为指定的新实例
     * @param router
     */
    public static void setInstance(MMSCRouter router) {
        instance = router;
    }

    /**
     * 做防御性复制
     * @return
     */
    public Map<String,MMSCInfo> getRouteMap() {
        return Collections.unmodifiableMap(deepCopy(routeMap));
    }

    private static Map<String, MMSCInfo> deepCopy(Map<String,MMSCInfo> map) {
        Map<String, MMSCInfo> result = new HashMap<>();
        for (Map.Entry<String,MMSCInfo> entry:map.entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
