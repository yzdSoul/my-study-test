package com.yzd.elastic.controller;


import com.yzd.elastic.dataobject.ESNewDO;
import com.yzd.elastic.service.SelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yzd on 2020/12/1
 */
@RestController
@RequestMapping("/search")
public class ElasticController {

    @Autowired
    private SelectService selectService;

    @GetMapping("/find")
    public ESNewDO findByTitle(@RequestParam String title) {
        ESNewDO newDO = selectService.findByTitle(title);
        return newDO;
    }

    @GetMapping("/findlike")
    public Page<ESNewDO> findByTitleLike(@RequestParam String title,@RequestParam Integer pageSize,@RequestParam String sortkey){
        if (sortkey==null) {
            sortkey = "createtime";
        }
        Sort sort = Sort.by(Sort.Direction.DESC, sortkey);
        if (pageSize == null) {
            pageSize = 5;
        }
        Pageable pageable = PageRequest.of(0, pageSize, sort);
        Page<ESNewDO> esNewDOS = selectService.findByTitleLike(title, pageable);
        return esNewDOS;
    }
}
