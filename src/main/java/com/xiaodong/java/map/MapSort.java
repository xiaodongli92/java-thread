package com.xiaodong.java.map;

import java.util.*;

/**
 * Created by xiaodong on 2017/5/26.
 */
public class MapSort {

    public static void main(String[] args){
        Map<String, Integer> map = new HashMap<>();
        map.put("aaa", 1);
        map.put("bbb", 3);
        map.put("ccc", 0);
        mapSortOut(map);
    }

    private static void mapSortOut(Map<String, Integer> map) {
        List<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        System.out.println(list.toString());
    }
}
