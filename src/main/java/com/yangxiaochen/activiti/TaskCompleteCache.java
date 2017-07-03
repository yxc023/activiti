package com.yangxiaochen.activiti;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yangxiaochen
 * @date 2017/6/30 20:22
 */
public class TaskCompleteCache {

    public static ThreadLocal<Set<String>> completingTaskIds = new ThreadLocal<>();

    public synchronized static Set<String> completingTaskIds() {
        if(completingTaskIds.get() == null) {
            completingTaskIds.set(new HashSet<>());
        }
        return completingTaskIds.get();
    }
}
