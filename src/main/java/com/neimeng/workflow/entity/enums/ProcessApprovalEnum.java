package com.neimeng.workflow.entity.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * 流程审批状态
 */
@Getter
public enum ProcessApprovalEnum {

    AGREE(1, "同意"),
    REJECT(2, "驳回"),
    END_PROCESS(3, "强制结束流程");

    private int code;
    private String value;

    ProcessApprovalEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public static ProcessApprovalEnum from(Integer code) {
        if (code == null) {
            return null;
        }
        return Arrays.stream(values()).filter(event -> event.getCode() == code).findFirst().get();
    }
}