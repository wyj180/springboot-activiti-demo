package com.neimeng.workflow.entity.vo;

import com.neimeng.workflow.entity.params.ApplyDatasetInfo;
import com.neimeng.workflow.entity.pojo.ProcessDataset;
import lombok.*;
import org.activiti.engine.task.Task;

/**
 * 前端展示任务信息
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaskVo extends ProcessDataset {

    private String taskId;

    private String taskName;

    public TaskVo(Task task) {
        this.taskId = task.getId();
        this.taskName = task.getName();
        setProcInstId(task.getProcessInstanceId());
    }
}
