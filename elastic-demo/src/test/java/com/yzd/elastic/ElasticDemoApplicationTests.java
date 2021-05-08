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
        String title = "#借K年利率是怎么计算的？可别算错了！#德云于谦：谁离不开谁？没听说过，谁捧着面碗看那玩意啊#吃辣椒能降低心血管疾病死亡率？医生说，确实有依据#出生就注定是千古明君的朱高炽，在位仅10个月，却能流芳百世#火车站有人找你借10元，为什么不能借？#德云王九龙：这还有脸说谁呢，你都黑到根了！瞧不起谁呢#费玉清难得一见翻唱《上海滩》，开嗓不输原唱，简直不要太好听#德云社这是专注毁歌啊！爷们被怼到心里发慌#古代女子结婚，为什么要穿“特殊”裤子？网友：长见识了！#不吃药，高血脂也能恢复正常！医生告诉你怎样降低甘油三酯#十二生肖之生肖虎，4月爱情变化，主动才有回报？#舌尖上的中国：这个老板有点个性，7张桌子，半天营业#德云社的高能观众，总会说出一些你意想不到的话语#寒夜命案：丈夫突遭暗杀，二十多年来案件仍未取得进展#花露水和八角放一起，太厉害了，搞定了男人女人的大难题，都要学#妃嫔给皇帝侍寝后，为什么都要搀扶着走路，原来是因为这个！#德云张鹤伦、郎鹤炎：哪个老话是这么说的？姥姥说也不行啊#四川达州：对老家总怀着依恋 再回老家已是仲春 农家正忙着春耕#小伙花8万块打造擎天柱告白，却遭涂磊怒斥：还不如去找工作#儿菜很多人都不会做，教你最靠谱的两种做法，最后遭殃的还是米饭#50岁男子半夜胸痛，主动脉撕裂半米长！医生：4个原因及早了解#50%以上的高血压患者，有睡眠问题！医生：4种类型打呼噜也算#全球首款激光武器即将问世，导弹都被直接熔化，威力堪比核武器#为何我们国内高速公路限制速度，德国却不限速？看完涨知识！#舌尖上的中国：这个豆腐的韧性好棒啊，这么大还不容易损坏#负债后自救才是上岸之路#为何在做手术时，医生要求患者把衣服都脱光？护士偷偷说了真相！#房主乔迁大喜，几十箱礼物从顶楼直接倾倒，哄抢场面十分壮观#三月飘雪气温回到冬天！儿媳猪蹄炖土豆，大炕头热乎啃肉真过瘾#上午八点，广西农村真人真事，专家都未解？#儿菜很多人喜欢吃不会做，加一块五花肉这样秘制，除了香就是下饭#家里水费交很多？用一根绳子，一年省下上百元水费，方法太省钱了#分手后想挽回，要戳中女人心里，不再低声下气求复合#为什么有些狗狗从出生时，就要把尾巴剪掉？#古代老爷睡觉时，还要让丫环守在一旁伺候，其中有何缘由？#刀郎都想不到，自己辛辛苦苦为谭咏麟写的歌，竟被小沈阳唱火了？#德云师徒之间真会遗传，老郭九良两人一唱一合的多好！#发现男人出轨，女人为什么不想离婚？是什么让女人忍受伤害#德云孙九香：我就给你个眼神你慢慢体会，要记住这一个错觉#十二生肖之生肖虎，月底财运要留意，关键点很简单？#抖音刷屏健身舞《乘风破浪的小姐姐》简单入门，好看又好学#为何日本人宁愿住网吧也不租房？看完日本的网吧后你就知道了#2021年新规给大家分享几个好消息#急！百亩农田干旱开裂，无水灌溉：荒好几年了#美国失去统治地位！中国新导弹威力巨大，一枚就能逼退航母群#美上将亲口承认 全球战力最强的国家仅有3个，剩下的不值一提#13岁女孩因感冒致心脏停跳。别大意，感冒久了可能会变心脏病#婆婆要回娘家扫墓, 儿媳又拿礼又给钱, 让婆婆体体面面走亲戚#歌坛天后王菲在北京丽高王府的豪宅别墅，自带泳池花园#婆婆给闺女买鸡过生日，妞妞看到生气了，婆婆的反应太温暖了#收网捕获稀奇的渔获，有尿袋的河豚鱼，圆鼓鼓的太惹人喜欢了#饭店拍黄瓜为什么那么好吃？原来是加了这个食材，真是后悔才学会#红霉素软膏加它真厉害，坚持抹一抹，皮肤皱纹色斑都消失不见了#德云孙九香：不扒拉你的话，一怒之下可以赏你一耳光#舌尖上的中国：这个火腿工厂好壮观啊，这些火腿都把我看饿了#常吃降压药的患者，要注意了！这个最常用的药物可能有致盲风险#分手后，急着拉黑微信的男人，基本上都是这3种心理！#德云社张九南怼天怼地怼观众！#十二生肖之生肖虎，3月下旬工作变化，升职加薪不是梦？#公园晨练太极剑，看架势是个高手，出招颇有气势#刀郎都想不到，自己这首歌还火到国外？老外一开嗓竟完全不输他！#美军舰在中国海周边秀肌肉，突被拦腰斩成两截#俯瞰北京城，首都夜景全景展现，太震撼了，不愧是世界超级大都市#固安解封第一天，高速路口收费站开车可出固安吗？可以去北京吗？#为什么公厕的门都要空一截？看似简单的设计，其实大有妙用#62岁父亲和儿子吵架，气到“暴聋”，医生都觉得惋惜#鸡蛋壳用火烤一烤，太厉害了，解决家家户户的大烦恼，看完记心里#舌尖上的中国：这个烤豆腐好神秘的感觉，真想尝一尝啊#山西朔州，带幼犬熟悉遛狗的节奏#董鄂妃有诸多版本，她到底有怎样的魅力？可以让顺治放弃江山呢？#德云孙九香：说也说不过，打也打不过，去你的吧#舌尖上的中国：这个烤羊片也太诱人了，不行了，口水收不住了#萌嘟嘟的小奶狗，12天就开始睁眼睛，先看看主人吧#纯干货！陈氏太极拳最全站桩视频，不收藏你就亏大了！#杜云峰老师展示武当太极剑，真是博大精深，好厉害啊#古代青楼消费很高吗？和现在相比有哪些差距？#2021、3月份最新政策#小小的下水道居住2000人，晚上才出来#古典优雅团扇舞《梅花渡》三位姐妹似天仙，飘飘然然下凡间#联力DK05F电脑桌机箱装机#合肥绕城高速交通情况：车流密集、交通相对顺畅#为何有人到农村收“废旧手机”，他们用来干啥？原来是个暴利行业#父母讨伐养女恩将仇报，女儿直接跳楼以证清白，网友：太吓人#72岁国足队长心梗离世，医生：春季预防心梗偷袭，8点要牢记#2021年最新好消息，逾期持卡人的福利来了#舌尖上的中国：这个民族一天三餐，基本都不能少了酸汤的#分手后，前任主动联系你，目的只有这几个#古代四大美女，个个都有生理缺陷，其中杨贵妃的最难以启齿！#大明崇祯皇帝是如何仅用短短三个月，把权倾一时的魏忠贤扳倒的？#无癌症的国家，90万人口无一人患癌症，究竟是何原因？#比亚迪宋Pro劲敌，外形大气时尚，吉利博越PRO值得购买吗？#十二生肖之生肖虎，4月事业变化，稳中求胜是关键？#二婚如何办理结婚证？如果没带这个“证件”，结婚想拿到手都难！#狗狗知道死亡吗？它们害怕吗？山西朔州训犬#美军实力不行了？美公开承认：中国海军已超越美国，俄发出警告#德云孙九香：再您的见吧！马上鞠躬离场了，这跟我没关系#乡村爱情：谢广坤竞争村委员输给刘能，面子挂不住，开始作妖了！#5种食物是天然“血管清道夫”，想要血管干净不堵塞，可以多吃#德云社之精彩时刻片段合集，小岳岳透露了重要信息#负债逾期后这种心态要不得#回答粉丝疑问，训练狗狗转圈，狗狗转半圈我们怎么办？#亚菲和亚宁偷吃桃酥，卫民跟安杰告状，结果反被打#车尾酷似比亚迪秦PLUS，搭激光雷达，小鹏P5实车曝光！#山西朔州，狗狗的特殊方式，了解了解#活了20多年才知道，丝袜这样穿才对，再也不担心下滑了#花盆里撒一把大米，没想到如此厉害，解决了家里的大烦恼，快试试#为何来中国的外国游客越来越少了？美国人说出了答案#感动又心酸！小女孩考了100分，回家后第一时间拿给“监控”看#农田里被村民忽略的绿色生物，有外地老板收购，出价500元1斤#薄姬干了什么，躲过了吕雉的陷害，还将儿子培养成千古一帝#当债务逾期后你是否每天很压抑？#“沙坦类”降压药，是护肾还是伤肾？医生为您讲清楚#十二生肖之生肖虎，4月整体变化，发展全靠理解？#美国能养11艘航母，那我国目前国力能养几艘呢？#快来看看用小米11Ultra拍的电影（片段）#动感健身舞一首《你莫走》歌词朴实，勾勒出纯美的乡村爱情#小伙立下豪言壮志，认为自己一定能拿下女友，却遭涂磊怒斥#活了20多年才知道，卫生纸一只都用错了，原来这样用才正确#德云社张九南秀绕口令！九成：我就问！还有谁不服？#错用导航迷了路，女孩着急路边哭：我好怕，越走越远#慈禧因为一道菜，竟下令处决一百多名御厨，只是因为这道菜羞耻#5个症状揪出血栓，医生提醒：别再硬撑，尽早治疗#闺蜜之间反目成仇，一个入狱一个毁容，儿子却对受害者情根深种#过期口红不要扔，涂在化妆棉上这样用，太厉害了，一年省上千百元#儿时的下饭菜虾酱烧豆腐，入味鲜香好吃下饭，记忆中的味道#家里茶几上，万万别放这三样物品，尤其第二样，可惜很多人没在意#三人同行采山珍，自己吃肉让妹妹喝汤，这分配模式够坑吧？#一件衣服卖20多万！涂磊羡慕不已，直呼：我要做裁缝#二婚妻子太过强势，丈夫不按她的意思办事，直接在家里动武#古代被抄家的大臣们，家中女眷是何“下场”，让现代人无法接受！#功夫大姐演练太极拳，动作优美舒展大方，忍不住多看了几遍！#德云张九龄：这是直系亲属吗，琢磨着邻居这都什么关系#分辨女人好不好追求，只需对她说三句话，你就知道了#21岁患有高血压。高血压是如何找上你的？心血管医生告诉你#那些常见的催收套路你有中过招吗#慈禧的最后一天：留下一道重要懿旨，临终前为何要口含夜明珠？#为何中国工人动不动就“辞职”？德国工人却能干到退休？原因现实#吃阿司匹林差点丧命! 碰到这种情况，不要吃阿司匹林，切记#当你负债累累陷入低谷，请记住这几句话#为什么日本冒天下之大不韪，也要捕食鲸鱼，原来背后有“秘密”！#德云孙九香：不用说的那么高大上，说吃面条就可以了#狗为什么要刨坑？它是怎么样丈量深度？埋的食物变质怎么办？#二弟为母亲向大哥发起进攻，为未知的事情吵得不开交，网友：傻子#为什么被冻死的人，大多都“衣不遮体”？专家说出实情#标配LED大灯，尾灯造型酷似奥迪A6L尾灯，体验新款#美军宣布开建新一代护卫舰，排水量6500吨，战力直追054A#饭店很受欢迎的蛋黄南瓜，教你在家轻松做，外香内糯看起来就馋人#十二生肖之生肖虎，月底财运要留意，结果顺利全看一点？#淳儿14岁入宫，为何皇帝养到17岁才侍寝？揭开后宫真实景象#炖鸡汤不要乱炖，这样炖出来汤鲜肉烂营养健康，有肉有菜有汤真爽#德云社一个个憋着造反，老郭表示心很累！#喝水时，这三种被子不要再买了，可能你家就有，懂的人不多，厉害#多宝鱼想要好吃有诀窍，教你秘制复合味汁，淋上去那真叫鲜美细嫩#舌尖上的中国：这叉烧包到底有多好吃？能一天卖出一千多笼#张桂英劝说安杰给德华找对象，还推荐老丁，安杰无奈#山下盖房不容易，打地基很费事，期待三层高楼完美收官#耳畔再响起毛宁金曲《涛声依旧》依然很美，怀旧广场舞#舌尖上的中国：这个豆腐看样子好好吃啊，真想尝尝这种浓重的风味#邓紫棋不愧是乐坛实力唱将！街头拿着简陋设备，却唱出演唱会效果#解读犬主们惯用的遛狗方式，你们的狗狗适合吗？山西朔州训犬#花椒和花露水放一起，一年省几百块，快提醒家人，早学会早受益#负债逾期后什么才是重要的#德云孙九芳：自己人不打自己人，再打我头围都要增生啦！#古代木驴什么样？今天在郓城县衙见到了，牢房里面真吓人#美军终于认清现实，五角大楼宣停产F35战机，启动四代机生产线#古代女子把“忠贞”看得十分重要，为何遇到采花贼时却不反抗？#美国使用高科技手段，全方位跟踪中国卫星，中国仅用一招直接化解#动感dj《月下情缘》32步健身舞，简单易学，轻松上手#固安可以进京啦！2月1日开车实拍固安进京需满足这些条件#3种“怪味豆”，有效帮助降血压，还能溶解血栓，预防冠心病#古代没有话筒和音响，将军是如何给几十万人训话的？#德云社最成功的“包袱”秦霄贤，听老秦的相声不让笑#来点不一样的设计，诺基亚N735G概念机#山西朔州，狗狗的投降行为，见过吗？#错乱的房号！当事人毫不知情房屋被卖，产权变更既成事实";
        List<String> titles = Arrays.asList(title.split("#"));
        Pageable pageable = PageRequest.of(0, 10, sort);
        for (int i = 0;i<titles.size();i++){
            try {
                    Page<ESNewDO> byTitle = productRepository02.findAllByTitle(titles.get(i),pageable);
                    if (byTitle.getContent().size()>1){
                        byTitle.getContent().stream().filter(news->news.getNewsType().equals("VIDEO") && news.getStatus().equals("ONLINE")).collect(Collectors.toList()).forEach(news-> System.out.println(news.getTitle()+"\t"+news.getId()+"\t"+news.getOrigin()+"\t"+sdf.format(news.getPubtime())));
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
        String str="江南三大名湖之一，和杭州西湖齐名，连乾隆都多次驻足停留#福州十大网红打卡地，夜游闽江，欣赏两岸的璀璨夜景#浙江的生态有机农庄，全部用有机肥料，打造人与自然和谐共生#嘉兴的历史文化街区，免费对外开放，感受江南府城的繁华#江苏历史悠久的城市，曾经以产盐而得名，如今却是旅游胜地#世界最大的丹顶鹤越冬地，聆听丹顶鹤的故事#江苏的绿水瀛洲，被誉为原始生态的世外桃源，你们来过吗？#苏北第一湖，有着世界最大的水上芦苇迷宫，入选吉尼斯纪录#中国郁金香第一花海，充满浓浓的荷兰风情，游客都慕名来打卡#三亚必打卡网红餐厅，5公里免费接送，人均100多吃到撑#福州的地标名山，形状如巨鳌，名胜古迹众多#三亚必吃的海鲜餐厅，人均100多吃到撑，5公里还免费接送#欧洲著名的西班牙广场，却不在西班牙，因《罗马假日》闻名于世#三亚必打卡美食，网红椰子鸡，游客都抢着来吃#中国四大名洞之一，福建最大石灰岩溶洞，被誉为“天然医院”#三亚必吃的海鲜餐厅，人均100多吃到撑，5公里还免费接送#中国最大的陆连岛，风景如画，怪石嶙峋，秦始皇三次登临#东海之上的“三座仙山”，再现蓬莱，游客络绎不绝来拜访";
        List<String> titles = Arrays.asList(str.split("#"));
        for (int i = 0;i<titles.size();i++) {
            try {
//                ESNewDO byTitle = productRepository02.findByTitle(titles.get(i).substring(0,10));
                ESNewDO byTitle = productRepository02.findByTitle(titles.get(i));
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
