package com.neimeng.workflow.helloword;

import com.neimeng.workflow.WorkflowApplicationTests;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Activiti 入门案例一
 */
public class HelloWorld01 extends WorkflowApplicationTests {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    /**
     * 第一步：部署流程定义
     */
    @Test
    public void deploymentProcessDefinition() {
        Deployment deployment = repositoryService
                .createDeployment()//创建一个部署对象
                .name("helloworld入门程序")//添加部署的名称
                .addClasspathResource("processes/helloworld.bpmn")//从classpath的资源中加载，一次只能加载一个文件
                .deploy();//完成部署

        System.out.println("部署ID：" + deployment.getId());
        System.out.println("部署名称：" + deployment.getName());
        System.out.println("部署Key：" + deployment.getKey()); // 这个key并不是我们画流程里面的key

        /**
         * 输出：
         * 部署ID：2501
         * 部署名称：helloworld入门程序
         * 部署Key：null
         */
    }

    /**
     * 第二步：启动流程实例
     */
    @Test
    public void startProcessInstance() {
        //根据流程定义的key启动流程
        String processDefinitionKey = "helloworld";

        ProcessInstance pi = runtimeService
                .startProcessInstanceByKey(processDefinitionKey);//使用流程定义的key启动流程实例，key对应bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动

        System.out.println("流程实例ID:" + pi.getId());//流程实例ID    101
        System.out.println("流程定义ID:" + pi.getProcessDefinitionId());//流程定义ID   key01:1:4

        /**
         * 流程实例ID:5001
         * 流程定义ID:helloworld:1:2504
         */
    }

    /**
     * 第三步：查询当前人的个人任务
     */
    @Test
    public void findMyPersonalTask() {
        String assignee = "王五";

        List<Task> taskList = taskService.createTaskQuery().processInstanceId("5001").list();
        System.out.println("当前任务处理人：" + taskList.get(0).getAssignee());
        System.out.println("指派任务新处理人：" + assignee);
        taskService.setAssignee(taskList.get(0).getId(), assignee);

        // 查询新处理人的任务
        List<Task> list = taskService
                .createTaskQuery()//创建任务查询对象
                .taskAssignee(assignee)//指定个人任务查询，指定办理人
                .list();

        printTaskInfo(list);
    }

    private void printTaskInfo(List<Task> list) {
        if (list != null && list.size() > 0) {
            for (Task task : list) {
                System.out.println("------------------------------------------------");
                System.out.println("任务ID:" + task.getId());
                System.out.println("任务名称:" + task.getName());
                System.out.println("任务的创建时间:" + task.getCreateTime());
                System.out.println("任务的办理人:" + task.getAssignee());
                System.out.println("流程实例ID：" + task.getProcessInstanceId());
                System.out.println("执行对象ID:" + task.getExecutionId());
                System.out.println("流程定义ID:" + task.getProcessDefinitionId());
                System.out.println("------------------------------------------------");
            }
        }
    }

    /**
     * 第四步：完成我的任务
     */
    @Test
    public void completeMyPersonalTask() {
        String taskId = "5005";  //任务ID
        Map<String, Object> variables = new HashMap<>();
        taskService.complete(taskId, variables);
        System.out.println("完成任务，任务ID = ：" + taskId);
    }

    @Test
    public void queryCurrentTask() {
        List<Task> list = taskService.createTaskQuery().processInstanceId("5001").list();
        printTaskInfo(list);
    }

}
