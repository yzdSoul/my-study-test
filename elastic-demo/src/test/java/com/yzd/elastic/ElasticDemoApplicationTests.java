package com.yzd.elastic;

import com.yzd.elastic.dataobject.ESNewDO;
import com.yzd.elastic.dataobject.ESProductDO;
import com.yzd.elastic.repository.ProductRepository;
import com.yzd.elastic.repository.ProductRepository02;
import com.yzd.elastic.repository.ProductRepository03;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class ElasticDemoApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductRepository02 productRepository02;
//    @Test // 插入一条记录
//    public void testInsert() {
//        ESProductDO product = new ESProductDO();
//        product.setId(1); // 一般 ES 的 ID 编号，使用 DB 数据对应的编号。这里，先写死
//        product.setName("芋道源码");
//        product.setSellPoint("愿半生编码，如一生老友");
//        product.setDescription("我只是一个描述");
//        product.setCid(1);
//        product.setCategoryName("技术");
//        productRepository.save(product);
//    }

    // 这里要注意，如果使用 save 方法来更新的话，必须是全量字段，否则其它字段会被覆盖。
    // 所以，这里仅仅是作为一个示例。
    @Test // 更新一条记录
    public void testUpdate() {
        ESProductDO product = new ESProductDO();
        product.setId(1);
        product.setCid(2);
        product.setCategoryName("技术-Java");
        productRepository.save(product);
    }

    @Test // 根据 ID 编号，删除一条记录
    public void testDelete() {
        productRepository.deleteById(1);
    }

    @Test // 根据 ID 编号，查询一条记录
    public void testSelectById() {
        Optional<ESProductDO> userDO = productRepository.findById(1);
        System.out.println(userDO.toString());
    }

    @Test // 根据 ID 编号数组，查询多条记录
    public void testSelectByIds() {
        Iterable<ESProductDO> users = productRepository.findAllById(Arrays.asList(1, 4));
        users.forEach(System.out::println);
    }

    @Test // 根据名字获得一条记录
    public void testFindByName() {
        ESProductDO product = productRepository.findByName("芋道源码");
        System.out.println(product);
    }
    @Test //
    public void testFindAllByName() {
        ESProductDO product = productRepository.findAllByName("芋道源码");
        System.out.println(product);
    }

    @Test // 使用 name 模糊查询，分页返回结果
    public void testFindByNameLike() {
        // 根据情况，是否要制造测试数据
//        if (true) {
//            testInsert();
//        }

        // 创建排序条件
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        // 创建分页条件。
        Pageable pageable = PageRequest.of(0, 10, sort);
        // 执行分页操作
        Page<ESProductDO> page = productRepository.findByNameLike("芋道", pageable);
        // 打印
        page.forEach(item -> System.out.println(item.toString()));
        System.out.println(page.getTotalPages());
    }

    /**
     * 为了给分页制造一点数据
     */
    private void testInsert() {
        for (int i = 1; i <= 100; i++) {
            ESProductDO product = new ESProductDO();
            product.setId(i); // 一般 ES 的 ID 编号，使用 DB 数据对应的编号。这里，先写死
            product.setName("芋道源码：" + i);
            product.setSellPoint("愿半生编码，如一生老友");
            product.setDescription("我只是一个描述");
            product.setCid(1);
            product.setCategoryName("技术");
            productRepository.save(product);
        }
    }

    @Test // 根据名字获得一条记录
    public void testFindByTitle() {
        // 创建排序条件
        Sort sort = Sort.by(Sort.Direction.DESC, "createtime");
        // 创建分页条件。
        Pageable pageable = PageRequest.of(0, 10, sort);
        Page<ESNewDO> product = productRepository02.findByTitleLike("苦命", pageable);
        product.forEach(item -> System.out.println(item.toString()));
    }


    @Autowired
    private ProductRepository03 productRepository03;

    @Test
    public void testSearch() {
        // 查找分类为 1 + 指定关键字，并且按照 id 升序

        Page<ESProductDO> page = productRepository03.search(1, "技术", PageRequest.of(0, 5, Sort.Direction.ASC, "id"));

        System.out.println(page.getTotalPages());

        // 查询分类为 1，并且按照 id 升序
        productRepository03.search(1, null,
                PageRequest.of(0, 5, Sort.Direction.ASC, "id"));
        System.out.println(page.getTotalPages());
    }

}
