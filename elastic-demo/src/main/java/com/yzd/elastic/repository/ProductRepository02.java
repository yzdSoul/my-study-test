package com.yzd.elastic.repository;

import com.yzd.elastic.dataobject.ESNewDO;
import com.yzd.elastic.dataobject.ESProductDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository02 extends ElasticsearchRepository<ESNewDO, Integer> {
    ESNewDO findByTitle(String title);

    Page<ESNewDO> findByTitleLike(String title, Pageable pageable);
}