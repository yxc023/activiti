package com.yangxiaochen.activiti;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangxiaochen
 * @date 2017/7/3 17:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitiConfiguration.class)
public class MainTest {
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;
    @Autowired
    ListenerService listenerService;
    @Test
    public void test() throws IOException {
        ClassPathResource resource = new ClassPathResource("bpmn/简单请假流程for基础功能测试.bpmn");
        Deployment deployment = repositoryService.createDeployment().name(resource.getFilename())
                .addInputStream(resource.getFilename(), resource.getInputStream()).deploy();

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("qing_jia_liu_cheng", new HashMap<>());

    }


    @Test
    public void test1() throws IOException {
        ClassPathResource resource = new ClassPathResource("bpmn/简单请假流程for基础功能测试.bpmn");
        Deployment deployment = repositoryService.createDeployment().name(resource.getFilename())
                .addInputStream(resource.getFilename(), resource.getInputStream()).deploy();

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("qing_jia_liu_cheng", new HashMap<>());

    }



    @Test
    public void test4() throws IOException {
        ClassPathResource resource = new ClassPathResource("bpmn/合同修改.bpmn");
        Deployment deployment = repositoryService.createDeployment().name(resource.getFilename())
                .addInputStream(resource.getFilename(), resource.getInputStream()).deploy();


    }

    @Test
    public void test03() throws Exception {

        Map<String, Object> param = new HashMap<>();
        param.put("_ACTIVITI_SKIP_EXPRESSION_ENABLED", true);
        param.put("hasDirector", true);
        param.put("hasManager", true);
        param.put("DIRECTOR_FLAG", "CQ3006|768");
        param.put("BUSINESS_MANAGER_FLAG", "CQ5072|765");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("managerOrDirector", param);

        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list().get(0);

        Map<String, Object> param2 = new HashMap<>();
        param2.put("name", "江北平1");
        param2.put("orgCode", "CQ5072");
        param2.put("postCode", "765");
        param2.put("userCode", "20116782");
        param2.put("userMobile", "13657644671");
        param2.put("AUDIT_FLAG", false);
        taskService.complete(task.getId(), param2);

        Thread.sleep(5000);

    }
}
