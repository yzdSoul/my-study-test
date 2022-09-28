package com.yzd.elastic.repository;

import com.yzd.elastic.dataobject.ESNewDO;
import com.yzd.elastic.dataobject.ESProductDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.sql.Timestamp;
import java.util.Date;

public interface ProductRepository02 extends ElasticsearchRepository<ESNewDO, Integer> {
//    @Query("{\"match\":{\"title\":{\"query\":\"?0\"}}}")
    Page<ESNewDO> findAllByTitle(String title,Pageable pageable);

    Page<ESNewDO> findAllByTitleLike(String title,Pageable pageable);

    ESNewDO findByTitle(String title);

    Page<ESNewDO> findByTitleLike(String title, Pageable pageable);

    ESNewDO findByOrigin(String origin);

    ESNewDO findByContent(String keyword);

}