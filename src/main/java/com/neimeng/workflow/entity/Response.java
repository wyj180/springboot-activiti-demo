package com.neimeng.workflow.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.ui.ModelMap;

@Getter
@Setter
@ToString
public class Response {

    private int code;
    private String desc;
    private Object data;

    public Response() {
    }

    public Response(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Response(int code, Object data, String desc) {
        this.code = code;
        this.data = data;
        this.desc = desc;
    }

    public Response(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public static Response success() {
        return new Response(ResponseCode.SUCCESS, ResponseDesc.System.SUCCESS);
    }

    public static Response success(Object data) {
        return new Response(ResponseCode.SUCCESS, data, ResponseDesc.System.SUCCESS);
    }

    /**
     * 请求失败，并返回失败业务码及失败原因
     *
     * @param message
     * @return
     */
    public static Response failure(String message) {
        return new Response(ResponseCode.FAILURE, message);
    }

    public ModelMap toModelMap() {
        String json = JSON.toJSONString(this);
        return JSONObject.parseObject(json, ModelMap.class);
    }
}