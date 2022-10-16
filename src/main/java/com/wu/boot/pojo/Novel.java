package com.wu.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@TableName("novel")
public class Novel {
    @TableId
    private Long id;
    private String title;
    private String img;
    private String author;
    private BigDecimal price;
    private Long novelNumber;
    private String information;
    private String novelAddress;
    private Integer isDeleted;

    @TableField(exist = false)
    private List<NovelChapter> novelChapters;
}
