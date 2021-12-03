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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
//        // 创建排序条件
        Sort sort = Sort.by(Sort.Direction.DESC, "createtime");
        // 创建分页条件。
        String title = "“黑滴效应”是什么？当两根手指慢慢靠近后，有趣的现象发生了##董事长回家发现母亲被弟弟关进小黑屋，当母亲被接走，弟弟慌了##女儿将岳母接回家住，女婿和岳母独自在家，女儿推开门后怒提离婚##董事长母亲低调回家探亲，儿子将母亲拒之门外，女儿做法感人##母亲上门找儿子借钱，走到门口听到儿子儿媳对话，儿媳做法感人##米饼小吃，好做也好吃！##曹丕见牛打架，要曹植作诗一首但不能有牛字，曹丕被骂千年却不知##姑娘跳河被大妈相救，掏出所有积蓄供姑娘上大学，5年后姑娘归来##大叔发明火灾逃生布袋，1分钟即可疏散20人，就像滑滑梯一样##女网红从吊塔摔下视频曝光，一脚踩空瞬间坠落，画面太揪心##湖南农村有座民国豪宅，建造者竟是一位逃兵，李宗仁白崇禧都来过##古代皇帝驾崩后，后宫妃嫔该怎么处理？有些下场让人难以启齿##爆笑：房东要涨价，路路说出男友职业，房东反而要退钱啊##爆笑：抠门祝晓晗餐厅吃饭，结账还能给老板上一课，是个狠人##新模式就来P城，落地就有“苟杂”，比捡空投香多了##狙击手麦克：吃鸡最强队友！全程趴着看戏，却助我成功1V8##董事长回村结婚，被村里人看不起惨遭鄙视，结婚当天全村后悔##植物大战僵尸：看不到阳光怎么打，窝瓜被矿工吞掉##植物大战僵尸：三花十八罐速攻流，两棵植物直接开打##勇敢牛牛不怕困难##辛巴燕窝事件大反转，判决辛巴胜诉，让厂家赔他一块钱##弟弟被哥哥赶出家门，弟弟记仇6年假装乞丐回家，哥嫂做法感人##强势婆婆教唆小姑子使坏，没想到儿媳突然进门，结局大快人心##无名烤肉除了食材新鲜，##蘸料也一绝！##蓝一：挑战不搜房区，用火箭上的物资吃鸡，落地就有吉利服";
        List<String> titles = Arrays.asList(title.split("##"));
        Pageable pageable = PageRequest.of(0, 10, sort);
        for (int i = 0;i<titles.size();i++){
            try {
                    Page<ESNewDO> byTitle = productRepository02.findAllByTitle(titles.get(i).substring(0,10),pageable);
                    if (byTitle.getContent().size()>=1){
                        byTitle.getContent().stream().filter(news->news.getNewsType().equals("NEWS") && news.getStatus().equals("ONLINE")).collect(Collectors.toList()).forEach(news-> System.out.println(news.getTitle()+"\t"+news.getId()+"\t"+news.getOrigin()+"\t"+sdf.format(news.getPubtime())));
                    }
                } catch (Exception e){
                System.out.println("未搜到："+titles.get(i));
                }
        }

//        byTitle.getContent().forEach(System.out::println);
//        System.out.println(byTitle);
    }

    @Test // 根据名字获得一条记录
    public void testFindByTitle() {
        String str="“黑滴效应”是什么？当两根手指慢慢靠近后，有趣的现象发生了##董事长回家发现母亲被弟弟关进小黑屋，当母亲被接走，弟弟慌了##女儿将岳母接回家住，女婿和岳母独自在家，女儿推开门后怒提离婚##董事长母亲低调回家探亲，儿子将母亲拒之门外，女儿做法感人##母亲上门找儿子借钱，走到门口听到儿子儿媳对话，儿媳做法感人##米饼小吃，好做也好吃！##曹丕见牛打架，要曹植作诗一首但不能有牛字，曹丕被骂千年却不知##姑娘跳河被大妈相救，掏出所有积蓄供姑娘上大学，5年后姑娘归来##大叔发明火灾逃生布袋，1分钟即可疏散20人，就像滑滑梯一样##女网红从吊塔摔下视频曝光，一脚踩空瞬间坠落，画面太揪心##湖南农村有座民国豪宅，建造者竟是一位逃兵，李宗仁白崇禧都来过##古代皇帝驾崩后，后宫妃嫔该怎么处理？有些下场让人难以启齿##爆笑：房东要涨价，路路说出男友职业，房东反而要退钱啊##爆笑：抠门祝晓晗餐厅吃饭，结账还能给老板上一课，是个狠人##新模式就来P城，落地就有“苟杂”，比捡空投香多了##狙击手麦克：吃鸡最强队友！全程趴着看戏，却助我成功1V8##董事长回村结婚，被村里人看不起惨遭鄙视，结婚当天全村后悔##植物大战僵尸：看不到阳光怎么打，窝瓜被矿工吞掉##植物大战僵尸：三花十八罐速攻流，两棵植物直接开打##勇敢牛牛不怕困难##辛巴燕窝事件大反转，判决辛巴胜诉，让厂家赔他一块钱##弟弟被哥哥赶出家门，弟弟记仇6年假装乞丐回家，哥嫂做法感人##强势婆婆教唆小姑子使坏，没想到儿媳突然进门，结局大快人心##无名烤肉除了食材新鲜，##蘸料也一绝！##蓝一：挑战不搜房区，用火箭上的物资吃鸡，落地就有吉利服";
        List<String> titles = Arrays.asList(str.split("##"));
        for (int i = 0;i<titles.size();i++) {
            try {
//                ESNewDO byTitle = productRepository02.findByTitle(titles.get(i).substring(0,10));
                ESNewDO byTitle = productRepository02.findByTitle(titles.get(i));
                String id = byTitle.getId();
                System.out.print(id+"\t");
                System.out.println(byTitle.getTitle());
            } catch (Exception e){
                System.out.println("未搜到："+titles.get(i));
            }


        }
    }

    @Test // 根据名字获得一条记录
    public void testFindByTitlewithCount() {
        String str="王者荣耀";
        List<String> titles = Arrays.asList(str.split("##"));
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, 10, sort);
        Timestamp ts = Timestamp.valueOf("2019-07-06 09:56:09");
        for (int i = 0;i<titles.size();i++) {
            try {
//                ESNewDO byTitle = productRepository02.findByTitle(titles.get(i).substring(0,10));
                Page<ESNewDO> byTitle = productRepository02.findAllByTitleLike(titles.get(i),pageable);
                System.out.println(byTitle.getNumberOfElements());
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
