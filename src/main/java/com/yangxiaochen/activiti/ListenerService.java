package com.yangxiaochen.activiti;

import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.task.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("listenerService")
public class ListenerService {

    static Logger logger = LogManager.getLogger(ListenerService.class);


    @Autowired
    private TaskService taskService;


    /**
     * 自动通过指定任务
     *
     * @param task
     * @param definitionKey task标示
     * @param key           通过或驳回key
     */
    public void autoCompleteByDefinitionKey(DelegateTask task, String definitionKey, String key) {
        logger.info("autoCompleteByDefinitionKey, task.id:{}", task.getId());
        String instanceId = task.getProcessInstanceId();
        Task taskTemp = taskService.createTaskQuery().processInstanceId(instanceId).taskDefinitionKey(definitionKey).singleResult();
        if (taskTemp != null) {


            if (TaskCompleteCache.completingTaskIds().contains(taskTemp.getId())) {
                return;
            }
            TaskCompleteCache.completingTaskIds().add(taskTemp.getId());


            logger.info("自动通过指定任务, taskid:{}, taskTempId:{}, definitionKey:{}, key:{}", task.getId(), taskTemp.getId(), definitionKey, key);
            final Map<String, Object> map = new HashMap<>();
            map.put(key, task.getVariable(key));
            final String taskTempId = taskTemp.getId();
            taskService.complete(taskTempId, map);
        }
    }
}
