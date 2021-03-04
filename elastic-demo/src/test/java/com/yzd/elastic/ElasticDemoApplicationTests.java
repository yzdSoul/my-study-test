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
        String str="贵州六盘水出现“迷你”高铁，全长5.2公里，穿越了童话世界!#江苏这座城市很特别，世界遗产不是一处，而是“一堆”#民间故宫的富商大宅被电视剧炒红，如今门票卖得比故宫还贵#越南有个山寨版桂林，主要游客来源还是中国人!#三亚摄氏30度以上，游客却纷纷前去避暑!#重庆第一网红景点，原本只是普通商业区，如今被“挤爆”!#日本最火爆的古都，成为暑假游热门地，到处可见中国人!#1500岁的银杏树你见过没有?就在贵州六盘水，景色美得像画!#陈小春结婚的非洲国家，看病不用钱，年轻人出去后却不想回来!#月薪四千多的新加坡人中午吃什么?两元钱的油鸡饭大排长龙!#中国最奢华的寺院，耗资40亿占地900亩，有唯一的佛顶骨舍利#北京旅行，很多人都走八达岭长城，其实慕田峪也很美！#湖州最有历史的一条步行街，光百年老年就有好几家，名字很特别#贵州最像“迷宫”的侗寨，走进来简单，走出去难，你知道为何吗？（2）";
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

    @Test // 根据名字获得一条记录
    public void testFindByOrigin() {
        String str="观邯郸,公安部网安局,高密发布,赣南日报,甘肃建设报,阜阳情报站,福建电视台现场,风云解读,法在身边栏目,独家责任,东南观察室,东方卫视这就是中国,电力发布,第一财经有料,邓州市广播电视台,德州日报,大兴安岭广播电视台,大河报郑州新闻,城市晚报,成都反邪教,兵工科技,北青即时,保定电视台公共频道,半月谈新媒体,百悠快报,百姓视点,安徽卫视真人秀,安徽卫视每日新闻报,FM103.1济南交通广播,CCTV4远方的家,CCTV1等着我,BTV影视风云,BTV加油吧孩子";
        List<String> origins = Arrays.asList(str.split(","));
        for (int i = 0;i<origins.size();i++) {
            try {
                ESNewDO byOrigin = productRepository02.findByOrigin(origins.get(i));
                System.out.println(byOrigin.getOrigin().equals(origins.get(i))+"\t"+byOrigin.getNewsType());
            } catch (Exception e){
                System.out.println("搜索超时："+origins.get(i));
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
