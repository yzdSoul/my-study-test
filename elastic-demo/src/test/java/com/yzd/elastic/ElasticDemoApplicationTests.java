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
import java.util.List;
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

        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        Page<ESProductDO> productDOS = productRepository.findAllByName("芋道源码", pageable);
        long totalElements = productDOS.getTotalElements();
        System.out.println(totalElements);
        productDOS.forEach(item -> System.out.println(item.toString()));
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
    public void testFindAllByTitle() {
//        // 创建排序条件
        Sort sort = Sort.by(Sort.Direction.DESC, "createtime");
        // 创建分页条件。
        Pageable pageable = PageRequest.of(0, 10, sort);
        Page<ESNewDO> byTitle = productRepository02.findAllByTitle("全球跨国企业首席财务官上调",pageable);
        byTitle.forEach(System.out::println);
//        System.out.println(byTitle);
    }

    @Test // 根据名字获得一条记录
    public void testFindByTitle() {
        String str="墨西哥女孩医院生子，孩子父亲竟才15岁，现两人决定共同抚养孩子#全球最锋利匕首，第1外观充满科技感，第2用石头打造，全身透明#这6个地方，夫妻相互亲过亲过3个以上，说明才算真爱#宫颈癌“露面”前，身体“3点异像”贼明显，女孩子别视而不见哟#男人过了60岁，还需要女人吗？大爷说出了心里话#这种水果或比盐伤肝，很多人喜欢吃，你可能是其中一个#已婚男子与弟媳产生情愫，周旋两女之间，没想到被逼签20万欠条#70岁夫妻，还需要夫妻生活吗？#足总杯：B 费百步穿杨萨拉赫双响难救主，曼联 3-2 逆转利物浦#这3种女人最容易成为情人，看看你身边有没有#石家庄黄庄公寓隔离场所建设争分夺秒与病毒赛跑#两个凶恶的嫂子冲进我家，抢走我的彩礼，还要抢我的嫁妆#炒前“必须焯水”的5种蔬菜，专家：再懒也别省这1步，别不懂装懂#笑话段子：一个让人心旷神怡的包装设计，设计师我要给你加鸡腿#妻子消失一夜，丈夫质问，妻子：跟前男友叙旧，啥也没干纯聊天#和男友睡觉，用什么姿势最舒服？#Liz Martinez 2021\"Sands\"秋冬婚纱系列 灵感来自沙漠地带";
        List<String> titles = Arrays.asList(str.split("#"));
        for (int i = 0;i<titles.size();i++) {
            try {
                ESNewDO byTitle = productRepository02.findByTitle(titles.get(i).substring(0,10));
                String id = byTitle.getId();
                System.out.println(id);
            } catch (Exception e){
                System.out.println("未搜到："+titles.get(i));
            }


        }
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
