package com.wu.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wu.boot.pojo.NovelChapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface NovelChapterMapper extends BaseMapper<NovelChapter> {

    Integer insertBatchSomeColumn(List<NovelChapter> entityList);

    /**
     * 分步查询
     * 查询小说章节以及对应的小说
     * 第一步
     */
    NovelChapter getNovelChapterAndNovelByStepOne(@Param("id") Long id);

    /**
     * 分步查询第二步
     * 查询小说以及对应的小说章节
     * 根据小说id查询小说章节信息 n_id = id
     * com.wu.boot.mapper.NovelChapterMapper#getNovelAndNovelChapterByStepTwo(java.lang.Long)
     */
    List<NovelChapter> getNovelAndNovelChapterByStepTwo(@Param("id") Long id);
}
