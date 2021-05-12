package com.yzd.elastic.controller;


import com.yzd.elastic.dataobject.ESNewDO;
import com.yzd.elastic.service.SelectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yzd on 2020/12/1
 */
@RestController
@RequestMapping("/search")
@Api(tags = "文章查询 API 接口")
public class ElasticController {

    @Autowired
    private SelectService selectService;

    @GetMapping("/find")
    @ApiOperation(value = "根据标题查询信息",notes = "作为测试只返回一条数据")
    public ESNewDO findByTitle(@RequestParam String title) {
        ESNewDO newDO = selectService.findByTitle(title);
        return newDO;
    }

    @GetMapping("/findAll")
    @ApiOperation("根据标题查询信息最多返回10条")
    @ApiImplicitParam(name = "title",value = "文章详情",paramType = "query",dataTypeClass =ESNewDO.class)
    public Page<ESNewDO> findAllByTitle(@RequestParam("title") String title){
        Page<ESNewDO> esNewDOS = selectService.findAllByTitle(title);
        return esNewDOS;
    }
}
