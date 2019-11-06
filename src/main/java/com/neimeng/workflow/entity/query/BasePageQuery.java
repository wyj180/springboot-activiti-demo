package com.neimeng.workflow.entity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel
@Getter
@Setter
@ToString
public class BasePageQuery {

    @ApiModelProperty(value = "页码", required = false)
    private int pageNum = 1;

    @ApiModelProperty(value = "每页条数", required = false)
    private int pageSize = 10;

}