package com.yzd.elastic.repository;

import com.yzd.elastic.dataobject.ESProductDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<ESProductDO, Integer> {
    ESProductDO findByName(String name);
    Page<ESProductDO> findAllByName(String name,Pageable pageable);
    Page<ESProductDO> findByNameLike(String name, Pageable pageable);
}