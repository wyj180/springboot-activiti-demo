package com.neimeng.workflow.entity.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * 流程状态
 */
@Getter
public enum ProcessStatusEnum {

    ONGOING(1, "审批中", "流程正在审批中"),
    FINISHED(2, "已完成", "正常审批完成"),
    CLOSED(3, "已终止", "强制结束流程");

    private int code;
    private String value;
    private String desc;

    ProcessStatusEnum(int code, String value, String desc) {
        this.code = code;
        this.value = value;
    }

    public static ProcessStatusEnum from(Integer code) {
        if (code == null) {
            return null;
        }
        return Arrays.stream(values()).filter(event -> event.getCode() == code).findFirst().get();
    }
}