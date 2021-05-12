package com.yzd.elastic.service.impl;

import com.yzd.elastic.dataobject.ESNewDO;
import com.yzd.elastic.repository.ProductRepository02;
import com.yzd.elastic.service.SelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by yzd on 2020/12/1
 */
@Service
public class SelectServiceImpl implements SelectService {

    @Autowired
    private ProductRepository02 productRepository02;

    @Override
    public ESNewDO findByTitle(String title) {
        ESNewDO esNewDO = productRepository02.findByTitle(title);
        return esNewDO;
    }

    @Override
    public Page<ESNewDO> findAllByTitle(String title) {
        // 创建排序条件
        Sort sort = Sort.by(Sort.Direction.DESC, "createtime");
        // 创建分页条件。
        Pageable pageable = PageRequest.of(0, 10, sort);
        Page<ESNewDO> news = productRepository02.findAllByTitle(title, pageable);
        return news;
    }
}
