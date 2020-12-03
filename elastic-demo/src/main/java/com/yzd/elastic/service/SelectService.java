package com.yzd.elastic.service;

import com.yzd.elastic.dataobject.ESNewDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by yzd on 2020/12/1
 */

public interface SelectService {
    ESNewDO findByTitle(String title);

    Page<ESNewDO> findByTitleLike(String title, Pageable pageable);

}
