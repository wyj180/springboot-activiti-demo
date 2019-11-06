package com.neimeng.workflow.controller;

import com.neimeng.workflow.service.process.ProcessHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/process/history")
public class ProcessHistoryController {

    @Autowired
    private ProcessHistoryService processHistoryService;

    /**
     * Purpose：获取流程图并显示
     *
     * @param processInstanceId 流程定义id
     * @param response
     * @return
     */
    @RequestMapping(value = "/getProcessImg/{processInstanceId}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public void getProcessImg(@PathVariable String processInstanceId, HttpServletResponse response) {
        processHistoryService.getProccessImage(processInstanceId, response);
    }

}
