package com.neimeng.workflow.utils;

import java.awt.Color;

/**
 * 流程常量
 */
public final class WorkflowConstants {

    // 默认流程信息
    public static final String DEFAULT_DS_PROCESS = "processes/Default_Dataset_Process.bpmn";
    public static final String DEFAULT_DS_PROCESS_NAME = "Default Dataset Process";
    public static final String DEFAULT_DS_PROCESS_KEY = "Default_Dataset_Process_Key";

    /**
     * 审批结果_流程变量名
     */
    public static final String APPROVAL_RESULT_VARIABLE_NAME = "approval_result";

    /**
     * 流程图颜色定义
     */
    public static final Color COLOR_NORMAL = new Color(0, 205, 0);
    public static final Color COLOR_CURRENT = new Color(255, 0, 0);

    /**
     * 生成流程图时的边距(像素)
     */
    public static final int PROCESS_PADDING = 5;

}