package com.neimeng.workflow.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neimeng.workflow.dao.ProcessDatasetMapper;
import com.neimeng.workflow.entity.pojo.ProcessDataset;
import com.neimeng.workflow.entity.query.BasePageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private ProcessDatasetMapper processDatasetMapper;

    public PageInfo<ProcessDataset> getProcessDatasetPageInfo(BasePageQuery pageQuery) {
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());
        Page<ProcessDataset> pageList = processDatasetMapper.selectList();
        return new PageInfo<>(pageList);
    }

}
