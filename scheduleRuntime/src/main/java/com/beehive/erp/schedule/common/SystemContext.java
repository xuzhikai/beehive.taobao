package com.beehive.erp.schedule.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xuzhikai on 2015/12/8.
 */
public class SystemContext {

    public static void setSystemMap(Map<String, String> systemMap) {
        for(Map.Entry<String,String> entry:systemMap.entrySet())
        {
            SystemMap.put(entry.getKey(),entry.getValue());
        }
    }

    private static Map<String, String> SystemMap = new ConcurrentHashMap<String, String>();

    public static String get(String key) {
        return SystemMap.get(key);
    }

    public static synchronized void put(String key, String value) {
        SystemMap.put(key, value);
    }

    public static synchronized void remove(String key) {
        SystemMap.remove(key);
    }
}
