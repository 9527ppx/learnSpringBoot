package com.wu.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wu.boot.pojo.Novel;
import org.apache.ibatis.annotations.Param;

public interface NovelMapper extends BaseMapper<Novel> {
    /**
     * 分步查询的第二步,通过n_id查询小说章节对应的小说
     */
    Novel getNovelChapterAndNovelByStepTwo(@Param("nId") Long nId);

    /**
     * 分步查询第一步
     * 获取小说以及对应小说章节的信息
     * 谁为主表谁在前
     */
    Novel getNovelAndNovelChapterByStepOne(@Param("id") Long id);
}
