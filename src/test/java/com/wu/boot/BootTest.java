package com.wu.boot;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wu.boot.mapper.NovelChapterMapper;
import com.wu.boot.mapper.NovelMapper;
import com.wu.boot.mapper.UserMapper;
import com.wu.boot.pojo.Novel;
import com.wu.boot.pojo.NovelChapter;
import com.wu.boot.pojo.User;
import com.wu.boot.util.novel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BootTest {

//    @Autowired
//    JdbcTemplate jdbcTemplate;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NovelChapterMapper novelChapterMapper;

    @Autowired
    private NovelMapper novelMapper;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    void contextLoads(){
//        Long aLong = jdbcTemplate.queryForObject("select count(*) from mysql.user", Long.class);
//        System.out.println("记录数"+aLong);
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void testRedis(){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("hello","word");
        String hello = operations.get("hello");
        System.out.println(hello);
    }

    //解析小说并且分章节
    @Test
    void textNovel() throws IOException {
        novel novel = new novel();
        List<String> strings = novel.cutFile();
        ArrayList<NovelChapter> novelChapterArrayList = new ArrayList<>();
        System.out.println(strings);
        strings.forEach(item->{
            NovelChapter novelChapter = new NovelChapter();
            novelChapter.setChapterName(item);
            novelChapter.setChapterAddress("D:\\novel\\诡秘之主\\"+item+".txt");
            novelChapter.setChapterNumber(1000L);
            novelChapter.setIsLocked(0);
            novelChapterArrayList.add(novelChapter);
        });
        novelChapterMapper.insertBatchSomeColumn(novelChapterArrayList);
    }

    @Test
    void textNovelName(){
        novel novel = new novel();
    }

    @Test
    void NovelChapterSelect(){
        NovelChapter novelChapter = novelChapterMapper.selectById(1576469050334232578L);
        System.out.println(novelChapter);

    }

    @Test
    void NovelChapterInsert(){
        NovelChapter novelChapter = new NovelChapter();
        novelChapter.setChapterName("测试");
        novelChapter.setChapterAddress("cs");
        novelChapter.setChapterNumber(1000L);
        novelChapter.setIsLocked(0);
        novelChapter.setNId(1576435627947212802L);
        novelChapterMapper.insert(novelChapter);
    }

    @Test
    void NovelInsert(){
        Novel novel = new Novel();
        novel.setTitle("cs");
        novel.setImg("cs");
        novel.setAuthor("wu");
        novel.setPrice(new BigDecimal(1000));
        novel.setNovelNumber(1000L);
        novel.setInformation("cs");
        novel.setNovelAddress("cs");
        novel.setIsDeleted(0);
        novelMapper.insert(novel);
    }

    @Test
    void SelectByStep(){
        //多对一
        NovelChapter novelChapter = novelChapterMapper.getNovelChapterAndNovelByStepOne(1L);
        System.out.println(novelChapter.getChapterName());
        System.out.println(novelChapter.getNovel().getTitle());
    }

    @Test
    void SelectNovelAndNovelChapterByStep(){
        //一对多
        Novel novel = novelMapper.getNovelAndNovelChapterByStepOne(1576435627947212802L);
        System.out.println(novel);
//        List<NovelChapter> novelChapters = novel.getNovelChapters();
//        for (NovelChapter novelChapter : novelChapters) {
//            System.out.println(novelChapter);
//        }
    }

    @Test
    void selectNovel(){
        Novel novel = novelMapper.selectById(1576435627947212802L);
        System.out.println(novel);
    }

    @Test
    void insertUser(){
        User user = new User();
        user.setUserName("admin");
        user.setUserPassword("123456");
        user.setUserImg("cs");
        user.setUserBalance(new BigDecimal(1000));
        userMapper.insert(user);
    }
    @Test
    void selectUser(){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        //查询条件
        wrapper.eq(User::getUserName,"admin");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }
    @Test//加密测试
    void BCryptPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }

}
