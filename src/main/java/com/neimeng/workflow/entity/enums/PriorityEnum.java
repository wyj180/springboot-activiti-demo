package com.neimeng.workflow.entity.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * 申请数据集的优先级
 */
@Getter
public enum PriorityEnum {

    NOMAL(1, "一般"),
    URGENT(2, "紧急"),
    SPECIAL_EMERGENCY(3, "特急");

    private int code;
    private String value;

    PriorityEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public static PriorityEnum from(Integer code) {
        if (code == null) {
            return null;
        }
        return Arrays.stream(values()).filter(event -> event.getCode() == code).findFirst().get();
    }
}