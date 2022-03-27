package com.example.oauthserver.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Component
public class ArticleApp {

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    //使用编程式事务
    public void addArticleTransation(String userId) {

        // 定义发布文章：编号、标题、内容、创建人 Hash
        HashMap<String, String> map = new HashMap<String, String>();
        //文章id，自增长，如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
        long articleId = stringRedisTemplate.opsForValue().increment("article:");
        map.put("title", "文章标题->" + articleId);
        map.put("content", "文章内容->" + articleId);
        map.put("user_id", userId);
        map.put("create_time", getCurrentDate());

        stringRedisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.multi();
                stringRedisTemplate.opsForHash().putAll("article:" + articleId, map);
                // 给自己投一票 Set
                stringRedisTemplate.opsForSet().add("vote:" + articleId, userId);
                // 给自己+10分 ZSet
                stringRedisTemplate.opsForZSet().add("score", articleId + "", 10);
                return redisOperations.exec();
            }
        });
        printArticle(articleId);
    }

    //使用注解式事务
    @Transactional
    public void addArticleAutoTransation(String userId) {
        // 定义发布文章：编号、标题、内容、创建人 Hash
        HashMap<String, String> map = new HashMap<String, String>();
        //文章id，自增长，如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
        long articleId = stringRedisTemplate.opsForValue().increment("article:");
        map.put("title", "文章标题->" + articleId);
        map.put("content", "文章内容->" + articleId);
        map.put("user_id", userId);
        map.put("create_time", getCurrentDate());
        stringRedisTemplate.opsForHash().putAll("article:" + articleId, map);

        // 给自己投一票 Set
        stringRedisTemplate.opsForSet().add("vote:" + articleId, userId);
        // 给自己+10分 ZSet
        stringRedisTemplate.opsForZSet().add("score", articleId + "", 10);
        printArticle(articleId);
    }

    /**
     * 发表文章
     * 逻辑：
     * 1.文章编号采用incr方法自增长
     * 2.定义Redis的Hash类型存放文章信息，键采用 "article:"+文章编号的方式
     * 3.定义Set用于记录哪些用户给这篇文章投过票，键采用 "vote:"+文章编号的方式
     * 4.定义ZSet用于记录每篇文章的分值，方便排序，键使用"score"
     */
    public void addArticle(String userId) {
        // 定义发布文章：编号、标题、内容、创建人 Hash
        HashMap<String, String> map = new HashMap<String, String>();
        //文章id，自增长，如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
        long articleId = stringRedisTemplate.opsForValue().increment("article:");
        map.put("title", "文章标题->" + articleId);
        map.put("content", "文章内容->" + articleId);
        map.put("user_id", userId);
        map.put("create_time", getCurrentDate());
        stringRedisTemplate.opsForHash().putAll("article:" + articleId, map);

        // 给自己投一票 Set
        stringRedisTemplate.opsForSet().add("vote:" + articleId, userId);
        // 给自己+10分 ZSet
        stringRedisTemplate.opsForZSet().add("score", articleId + "", 10);
        printArticle(articleId);
    }

    /**
     * 获取当前时间
     * @return
     */
    public String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 日期时间转字符串
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        System.out.println(now.getNano());
        return nowText;
    }

    /**
     * 获取文章信息,参数是文章的id
     * @param articleId
     */
    private void printArticle(long articleId) {
        /* 打印日志 */
        System.out.println("***********************");
        List<Object> list = stringRedisTemplate.opsForHash().multiGet("article:" + articleId, Arrays.asList("article:" + articleId, "title", "content", "user_id", "create_time"));
        System.out.println("文章编号:" + articleId);
        System.out.println("文章标题:" + list.get(1));
        System.out.println("文章内容:" + list.get(2));
        System.out.println("发布用户:" + list.get(3));
        System.out.println("发布时间:" + list.get(4));
        System.out.println("***********************");
        double card = stringRedisTemplate.opsForZSet().score("score", articleId + "");
        System.out.println("文章分值:" + card);

        Set<String> set = stringRedisTemplate.opsForSet().members("vote:" + articleId);
        System.out.println("给文章投票的用户有:" + String.join(",", set));
    }

    /**
     * 给文章投票
     * 逻辑：
     * 1.记录文章投票用户集合的Set新增一条数据sadd，如果返回1表示新增成功，返回0表示已存在
     * 2.记录分值的ZSet调用zincrby方法加10分
     */
    public void voteArticle(long articleId, String userId) {

        // 查找文章
        if (!stringRedisTemplate.hasKey("article:" + articleId)) {
            System.out.println("文章:" + articleId + " 不存在.");
            return;
        }
        // 用户投票
        if (stringRedisTemplate.opsForSet().add("vote:" + articleId, userId + "") == 0) {
            System.out.println("用户：" + userId + " 已对文章:" + articleId + " 进行过投票，不可重复投票");
            return;
        }
        // 投票成功+10分
        stringRedisTemplate.opsForZSet().incrementScore("score", articleId + "", 10);
        printArticle(articleId);
    }

    /**
     * 按分值从高到低排序
     * 1.调用ZSet的zrevrange方法根据分值倒序，获得一个集合
     * 2.循环集合去Hash中查询文章信息
     */
    public void articlesSortedByVote() {
        Set<String> set = stringRedisTemplate.opsForZSet().reverseRange("score", 0, 1000);// 返回的是文章编号
        for (String articleId : set) {
            System.out.println("<<=================================================>>");
            printArticle(Long.parseLong(articleId));
        }
    }
}
