package com.yzd.elastic.service;

import com.yzd.elastic.dataobject.ESNewDO;
import org.springframework.data.domain.Page;

/**
 * Created by yzd on 2020/12/1
 */

public interface SelectService {
    ESNewDO findByTitle(String title);

    Page<ESNewDO> findAllByTitle(String title);

}
