package com.wu.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wu.boot.mapper.NovelChapterMapper;
import com.wu.boot.pojo.NovelChapter;
import com.wu.boot.service.NovelChapterService;
import org.springframework.stereotype.Service;

@Service
public class NovelChapterServiceImpl extends ServiceImpl<NovelChapterMapper, NovelChapter>
        implements NovelChapterService {
}
