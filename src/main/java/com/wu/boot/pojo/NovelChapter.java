package com.wu.boot.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

//小说章节表
@TableName("novel_chapter")
@Data
public class NovelChapter {
    private Long id;
    private String chapterName;
    private String chapterAddress;
    private Long chapterNumber;
    private Integer isLocked;
    private Long nId;

    //当前章节属于哪个小说
    @TableField(exist = false)
    private Novel novel;
}
