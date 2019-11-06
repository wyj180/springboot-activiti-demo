package com.neimeng.workflow.service.process;

import java.util.List;


import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 流程定义相关Service
 */
@Slf4j
@Service
@Transactional
public class ProcessRepositoryService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProcessTaskService processTaskService;

    /**
     * 获取所有部署流程信息
     *
     * @return
     */
    public List<Deployment> deployList() {
        return repositoryService.createDeploymentQuery().list();
    }

    /**
     * 根据任务ID获取流程定义
     *
     * @param taskId
     * @return
     */
    public ProcessDefinition findProcessDefinitionByTaskId(String taskId) {
        Task task = processTaskService.getTaskByTaskId(taskId);
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(task.getProcessDefinitionId()).singleResult();
        return processDefinition;
    }

    /**
     * 根据流程定义key获取相关流程定义对象列表
     *
     * @param processDefinitionKey
     * @return
     */
    public List<ProcessDefinition> getProcessDefinitionsByKey(String processDefinitionKey) {
        return repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey)
            .orderByProcessDefinitionVersion().desc().list();
    }

    /**
     * 获取最新的流程定义信息
     *
     * @param processDefinitionKey
     * @return
     * @throws Exception
     */
    public ProcessDefinition getProcessDefinitionByKey(String processDefinitionKey) {
        return repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey)
            .orderByDeploymentId().desc().list().get(0);
    }

    /**
     * 根据流程定义id获取所有定义对象
     *
     * @param processDefinitionId
     * @return
     */
    public ProcessDefinition getProcessDefinitionById(String processDefinitionId) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId).singleResult();
        return processDefinition;
    }

    /**
     * 根据流程定义id获取流程定义实体
     *
     * @param processDefinitionId
     * @return
     * @throws Exception
     */
    public ProcessDefinitionEntity getProcessDefinitionEntity(String processDefinitionId) {
        return (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                .getDeployedProcessDefinition(processDefinitionId);
    }

    /**
     * 新增模型
     *
     * @return
     */
    public Model newModel() {
        return repositoryService.newModel();
    }

    /**
     * 保存模型
     *
     * @param model
     */
    public void saveModel(Model model) {
        repositoryService.saveModel(model);
    }

    /**
     * 添加模型信息
     *
     * @param modelId
     * @param bytes
     */
    public void addModelEditorSource(String modelId, byte[] bytes) {
        repositoryService.addModelEditorSource(modelId, bytes);
    }

    /**
     * 获取模型信息列表
     *
     * @return
     */
    public List<Model> getModelsOrderByCreatetime() {
        return repositoryService.createModelQuery().orderByCreateTime().desc().list();
    }

    /**
     * 部署流程
     *
     * @param modelName
     * @param processName
     * @param bpmnBytes
     */
    public void deployeModel(String modelName, String processName, byte[] bpmnBytes) {
        repositoryService.createDeployment().name(modelName).addString(processName, new String(bpmnBytes)).deploy();
    }

    /**
     * 根据modelId获取模型信息
     *
     * @param modelId
     * @return
     */
    public Model getModelByModelId(String modelId) {
        return repositoryService.getModel(modelId);
    }

    /**
     * 根据流程定义ID获取流程定义信息
     *
     * @param modelId
     * @return
     */
    public byte[] getModelEditorSourceByModelId(String modelId) {
        return repositoryService.getModelEditorSource(modelId);
    }

    /**
     * 根据流程定义ID获取流程定义信息
     *
     * @param processDefinitionId
     * @return
     */
    public BpmnModel getModel(String processDefinitionId) {
        return repositoryService.getBpmnModel(processDefinitionId);
    }
}