package com.neimeng.workflow.service.process;

import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 运行中的流程Service
 */
@Slf4j
@Service
@Transactional
public class ProcessRuntimeService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ProcessTaskService processTaskService;

    /**
     * 根据流程定义key启动流程实例
     *
     * @param definitionKey
     * @return
     */
    public ProcessInstance startProcessInstanceByKey(String definitionKey) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(definitionKey);
        return processInstance;
    }

    /**
     * 通过流程定义id启动流程
     *
     * @param processDefinitionId
     * @return
     */
    public ProcessInstance startProcessInstanceByDefId(String processDefinitionId) {
        return runtimeService.startProcessInstanceById(processDefinitionId);
    }

    /**
     * 通过流程实例id获取流程实例
     *
     * @param processInstanceId
     * @return
     */
    public ProcessInstance getProcessInstance(String processInstanceId) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        return processInstance;
    }

    /**
     * 获取活动节点信息
     *
     * @param executionId
     * @return
     */
    public List<String> getActiveActivityIds(String executionId) {
        return runtimeService.getActiveActivityIds(executionId);
    }

    /**
     * 获取执行实例信息
     *
     * @param executionId
     * @return
     */
    public ExecutionEntity getExecutionEntityByExecutionId(String executionId) {
        return (ExecutionEntity) runtimeService.createExecutionQuery().executionId(executionId).singleResult();
    }

    /**
     * 通过任务id获取流程实例
     *
     * @param taskId
     * @return
     */
    public ProcessInstance getProcessInstanceByTaskId(String taskId) {
        Task task = processTaskService.getTaskByTaskId(taskId);
        return getProcessInstance(task.getProcessInstanceId());
    }

    /**
     * 获取运行时的流程变量的值
     *
     * @param executionId
     * @param variableName
     * @return
     */
    public Object getVariable(String executionId, String variableName) {
        return runtimeService.getVariable(executionId, variableName);
    }

    /**
     * 根据流程实例id获取相关变量
     *
     * @param executionId
     * @return
     */
    public Map<String, Object> getVariables(String executionId) {
        return runtimeService.getVariables(executionId);
    }

    /**
     * 根据流程实例ID获取执行实例
     * 注意：可能是多个执行实例ID，比如并行任务的情况
     *
     * @param processInstanceId
     * @return
     */
    public List<Execution> getExecutionByPid(String processInstanceId) {
        return runtimeService.createExecutionQuery().processInstanceId(processInstanceId).list();
    }

    /**
     * 根据流程定义的Key获取所有正在运行的执行实例
     *
     * @param processDefinitionKey
     * @return
     */
    public List<Execution> getExecutionEntityByPKey(String processDefinitionKey) {
        return runtimeService.createExecutionQuery().processDefinitionKey(processDefinitionKey)
            .orderByProcessInstanceId().desc().list();
    }

    /**
     * 判断流程是否结束
     *
     * 说明：true:已结束   false:还没结束
     *
     * @param processInstanceId
     * @return
     */
    public boolean processIsEnd(String processInstanceId) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        if (processInstance == null) {
            return true;
        }
        return false;
    }

    /**
     * 挂起流程
     *
     * @param processInstanceId
     */
    public void suspendProcess(String processInstanceId) {
        runtimeService.suspendProcessInstanceById(processInstanceId);
    }
}
