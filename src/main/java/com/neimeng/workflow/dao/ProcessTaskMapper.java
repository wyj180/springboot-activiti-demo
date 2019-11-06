package com.neimeng.workflow.dao;

import com.neimeng.workflow.entity.pojo.ProcessTask;

import java.util.List;

public interface ProcessTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProcessTask record);

    int insertSelective(ProcessTask record);

    ProcessTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProcessTask record);

    int updateByPrimaryKeyWithBLOBs(ProcessTask record);

    int updateByPrimaryKey(ProcessTask record);

    /**
     * 根据流程实例ID查询审批历史记录
     *
     * @param processInstanceId
     * @return
     */
    List<ProcessTask> selectByProcessInstanceId(String processInstanceId);
}