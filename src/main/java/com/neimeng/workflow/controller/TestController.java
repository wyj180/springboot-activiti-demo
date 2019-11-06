package com.neimeng.workflow.controller;

import com.github.pagehelper.PageInfo;
import com.neimeng.workflow.dao.ProcessDatasetMapper;
import com.neimeng.workflow.entity.Response;
import com.neimeng.workflow.entity.pojo.ProcessDataset;
import com.neimeng.workflow.entity.query.BasePageQuery;
import com.neimeng.workflow.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.xalan.xsltc.compiler.util.MethodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "测试接口 ")
@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private ProcessDatasetMapper processDatasetMapper;

    @GetMapping("test01")
    @ResponseBody
    public Response test01() {
        String result = "success";
        return Response.success(result);
    }

    @ApiOperation("分页查询")
    @GetMapping("testPage")
    @ResponseBody
    public Response testPage(BasePageQuery pageQuery) {
        PageInfo<ProcessDataset> pageInfo = testService.getProcessDatasetPageInfo(pageQuery);
        return Response.success(pageInfo);
    }

}
