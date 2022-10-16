package com.wu.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wu.boot.mapper.NovelMapper;
import com.wu.boot.pojo.Novel;
import com.wu.boot.service.NovelService;
import org.springframework.stereotype.Service;

@Service
public class NovelServiceImpl extends ServiceImpl<NovelMapper, Novel>
        implements NovelService {
}
