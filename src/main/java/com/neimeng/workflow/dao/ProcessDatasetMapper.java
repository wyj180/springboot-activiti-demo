package com.neimeng.workflow.dao;

import com.github.pagehelper.Page;
import com.neimeng.workflow.entity.pojo.ProcessDataset;
import com.neimeng.workflow.entity.vo.TaskVo;

public interface ProcessDatasetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProcessDataset record);

    int insertSelective(ProcessDataset record);

    ProcessDataset selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProcessDataset record);

    int updateByPrimaryKey(ProcessDataset record);

    /**
     * 根据流程实例ID查询关联的数据集基本信息
     *
     * @param processInstanceId
     * @return
     */
    ProcessDataset selectByProcessInstanceId(String processInstanceId);

    /**
     * 查询用户需要处理的任务
     *
     * @param assignee
     * @return
     */
    Page<TaskVo> getTasksByAssignee(String assignee);

    /**
     * 分页查询数据集-流程信息
     *
     * @return
     */
    Page<ProcessDataset> selectList();
}