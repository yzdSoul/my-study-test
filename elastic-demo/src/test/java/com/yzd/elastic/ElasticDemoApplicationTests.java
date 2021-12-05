package com.yzd.elastic;

import com.yzd.elastic.dataobject.ESNewDO;
import com.yzd.elastic.dataobject.ESProductDO;
import com.yzd.elastic.repository.ProductRepository;
import com.yzd.elastic.repository.ProductRepository02;
import com.yzd.elastic.repository.ProductRepository03;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        String title = "泪目!浙江一老人临终前执意等法官，就是想看到女儿还清欠款#第一次来浙江的货车司机低头看了一眼导航，一死两伤四车受损#面向小区中心广场的C位住房，变成广场舞音乐房，“天天唱响”的烦恼你家小区有吗#初二女生突然性情大变、越吃越瘦，医生说青春期女孩要警惕此病#浙江女子为情所困举菜刀想轻生，关键时刻民警朝她后背开了一“枪”#滴滴豪华车杭州上线100天，到底哪些人在乘坐#杭州竞舟小学举办家校篮球足球联赛 老爸手上脚下有点功夫 娃在这所学校还真有面子#老奶奶杭州闹市迷途还忘了自己的姓名 民警还是帮她找到了家#外资驰援VS美股大跌 A股会怎么走#浙江安防双雄门槛快被踩破，200多家机构扎堆前来找什么#最受避暑游客欢迎的十大县级城市，前三都在浙江#全国首创 杭州高速上新增10个免费“防疲劳停车区”#行拘五日加罚款!杭州那辆一夜失踪的暂扣工程车找到了!#灯光熠熠，照亮你我，杭州上德立交景观照明全新亮相#泊位?彩色的?没错，杭州万塘路上多了一批五彩泊位，背后做的是这篇文章#这个14岁的浙江女孩太棒了!她是王嫣笑 也是王女侠!今天请向她学习!#国合会2019年会正式开幕 千名专家政要齐聚杭州#浙商大女学霸考研上了复旦，开公众号把备考经验分享给学弟学妹#杭州那些中签就像中奖的网红盘，不用摇号也能买到!方法是……#杭州这几个签约率99以上的楼盘有何神奇#突发!杭州一4岁小女孩从10楼坠下#点赞!单腿支撑做完手术!杭州富阳有位心系患者的“拄拐医生”!#女生在杭租房险被房东强奸，案子今天判了!房东犯强奸罪获刑两年#点赞!今天杭州至武义高铁上一男子大量呕血!这位女医生果断出手!#全国双创周即将开幕 记者提前探访杭州梦想小镇主场馆#惊了!杭州6岁男孩的牙齿上“长”出来一根橡皮圈!#牢记安全啊!杭州一27岁小伙昨晚溺水失踪，今早坏消息传来#杭州留下多少航空故事?他们来这里寻访老一辈足迹#人人人人人，车车车车车，最近杭州这个凉凉的地方爆红了#[深读]8年了，在杭州被高空坠物砸到截肢的朱依依，你还好吗#杭州5岁女孩幼儿园回来后直喊“小屁屁疼”医生诊断：秘密地带受伤了!#重磅!贯彻长三角一体化发展战略，杭州打算这么做#逃到天边也枉然!涉网贷案诈骗的“富姐”徐丹娟落网了#杭州最“热”的良渚古城遗址公园，明天开始要收费了#浙江营商环境哪里好?这份浙江大学发布的研究报告告诉你#杭州余杭市场监督管理局办公室有个“镇馆之宝”热水器 背后的故事……#总直不起腰的18岁山西少女，暑假来了杭州后，不想回去了#全班最矮的孩子们注意了!这是一个能让你个头窜一窜的活动#这个杭州男人在家里造的飞机要上天：谁有胆量，我带他一起飞#这就尴尬了，暑期“一座难求”的杭州图书馆，沙发区域成了“睡吧”#杭州阿姨天天喝防暑药依旧中招，医生说，喝错反倒更易中暑#1分钟体验不过瘾，有人想把无边际泳池搬回家#2019浙江省公务员招考笔试结束 申论让考生直呼“难”#8日曾有人在杭州失联女童搜索海域附近发现一具疑似尸体#端午假期酒店预订量翻倍，杭州仅次京沪排第三#杭州城西这个男厕如厕时频频被偷拍 没想到偷拍他们的也是男人……#杭州多板块一二手房价格倒挂消失!红利盘还剩这些……#杭州给柳树打针，抑制有效性99%!柳絮明年会更少#杭州女童景观灯触电事件带来反思杭州市景观灯将全面“体检”#杭州三墩附近三车追尾，4人受伤，其中一位是孕妇#杭州市拱墅区今年秋季将投入使用10所新学校#杭州帅气男主播跳槽，公司索要1000万违约金，这样的事情一般法院会怎么判#——记浙江省最美退役军人、浙江上百集团有限公司董事长兼总经理骆左强#坑?省消保委从杭州13家菜场抽检了68份菜 只有8份足秤#来不及悲伤!刚结束安吉增援的杭州消防员看到有人落水，奋不顾身跳水救人#老爸破产后妈不给生活费，杭州小伙恋上苦命“大学生陪酒女”，但是剧情发展太意外#凉拌黑木耳又出事!杭州40岁男子吃完第二天失去意识#全省首创无人车管所 杭州“智慧车管”新模式更快更方便#全省首个!杭州检方为退役军人提供司法救助#税收半年报发布!浙江上半年累计新增减税降费近千亿元#这叫先礼后兵?每天早上5点杭州这个小区都被摇门声吵醒 只因86岁大爷……#浙江新闻评论：有人登上珠峰，有人跌进深渊";
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
        String str="激情四射？何超莲说李佳琦偷看黄明昊换衣服，周围人眼神都变了 ##张彬彬玩狼人杀被王彦林忽悠，发现被骗后恼羞成怒：我还是错付了 ##汪涵埋怨妻子，15年婚姻亮红灯：杨乐乐的流泪，是所有女性的悲哀 ##幸亏赵丽颖足够清醒，不然她也在吴亦凡的射程之内 ##谢霆锋小时候颜值超高！竟被谢贤批评是傻子，直言：和妈妈打麻将 ##千万别让赵本山去颁奖了！一上台就调侃女明星，台下大咖都笑翻了 ##黄渤：王宝强很会利无辜的眼神！连老梁都坐不住，明星评价王宝强 ##倪妮穿白色吊带裙大秀火辣身材，与老外交流落落大方获网友夸赞 ##小s称舒淇比林志玲好看：但更我比差一点！明星们评价舒淇长相 ##刘嘉玲走秀气场全开，梁朝伟紧张到脚趾扣地，像社恐遇上社交达人 ##和杨紫拍吻戏肖战“努嘴”，注意手咋放的？嫌不嫌弃一目了然 ##网曝刘浩存妈妈培训班因下腰不当，致学员下肢瘫痪 ##狼牙一断吴京倒，一百个保镖都打赢了，最终死在了娘们手中 ##董卿张蕾比口才：同样调侃明星大咖差距一目了然，没对比就没伤害 ##闺蜜扯下祖海多年“遮羞布”，揭秘不婚真相，难怪没人娶！ (2) ##林依轮：凭借爱情鸟红遍乐坛，为了吃它做的饭，大咖都要提前预约 ##高秀敏为什么至死没有原谅赵本山？在她葬礼上，听听何庆魁怎么说 ##恬不知耻林月云：曾在孕期屡次不忠，竟然与女婿十指紧扣毫不避嫌 ##穆桂英挂帅穆桂英太厉害了，改变了婆婆，婆婆竟让元帅给她洗脚 ##窦骁李沁虐恋来了！雷宇峥强吻杜晓苏又甜又虐，网友：心都在滴血 ##孟子义娇羞问张翰：你会想我吗？随后张翰的回答，暴露真实人品 ##爆笑：这年头参加漫展的妹子不穿少点，想火都难 ##沙雕段子：小时候我为拥有大把零花钱，结果坑爹坑妈坑全家，爆笑 ##陈翔六点半：究竟是你手段高明，还是套路太深？居然这样对我！ ##不同食物放进真空中，一个月后还能吃吗？网友：确定没过保质期？ ##《你微笑时很美》：抄袭融梗恶心至极，3.0分都给多了 ##男孩意外获得超能力，吃下女同学的发带，就能控制对方的身体 ##万绮雯巅峰颜值合集，量出腿长43寸，不愧是参加过亚洲小姐的人 ##张冬玲：曾被称超女大妈，走红后被误会阿宝妻子，现在模样大变 ##新歌《莲》震惊老外，看到中国龙直呼太酷了，老外看艺兴mv名场面 ##剿匪：师长得知女秘书是奸细，故意在死刑名单上加一人，奸细慌了 ##疑似被贾乃亮以“剪头发”梗拒绝后，李小璐再发感慨引人心疼 ##女明星对自己有多狠？40岁李小璐最胖不过90斤，杨幂的回答扎心了 ##张常宁与老公吴冠希约会1日游，直言：这过山车限高，张常宁采访 ##重庆妹儿斗是乖，单身小哥哥抓紧机会哦 ##医生建议吃清淡，但是喜欢看老婆吃香喝辣开心的样子 ##真实回应，太霸气了，哈哈 ##【德云社】岳岳至今难忘的人生经历，若以德报怨何以报德？伤害过后再无原谅 ##小姐姐给相距1000公里外的男友打了个电话 ##恋爱时怎么控制沟通的频率呢？ ##领了结婚证以CJC得很厉害应该及时止损L婚吗？ ##两个人即使亲密无间，也要保持相对的礼仪和仪式感 ##恋爱讲究价值平等，如何客观的评定自己呢？ ##在感情中对方犯错了，选择了接纳，但还是走不出来 ##我说得也没毛病啊，这个客人真奇怪 ##当孟非碰上郭德纲，一句话把老郭呛得哑口无言，台下大咖都笑翻了 ##千万不要让沈腾碰到沙溢，俩人一开口笑死人不偿命，简直是俩活宝 ##明星暴露私下真实人品？看黄磊李健对残疾人的态度，差距一目了然 ##沈腾教老外说绕口令，结果却把自己绕懵了！明星教老外绕口令合集 ##关晓彤专治郭麒麟合集，一句话就让大林无言以对，台下众星乐坏了 ##微博之夜黄教主与夫人“天各一方”Baby依附教主成就今时的自己 ##大张伟猜词结巴，被华晨宇截胡气到不行！让大张伟吃瘪的明星们 ##黄渤和刘亦菲是同学，直言：我吃亏在脸上！是大学同学的明星们 ##王小玮携新搭档亮相：搭档徐子崴帅气又实力，颜值完全不输王小海 ##蒋大为是预言家？农民式狡猾朱之文剧场倒闭后，又被朋友告侵权";
        List<String> titles = Arrays.asList(str.split("##"));
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
