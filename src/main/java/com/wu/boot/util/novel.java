package com.wu.boot.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class novel {
    //解析小说
    public List<String> cutFile() throws IOException {
        //定义一个字符串用来储存读入的小说内容
        String src = "";
        String name = "001";
        //文件输入流，个人喜欢用流
        FileInputStream fis = null;
        try {
            //从指定路径读取小说
            fis = new FileInputStream("D:\\novel\\诡秘之主.txt");
            name = new File("D:\\novel\\诡秘之主.txt").getName();
            String[] split = name.split("\\.");
            name = split[0];
            byte[] bt = new byte[5440];//一个页面5440字节
            int i = 0;
            //for循环读取数据，保存在src中
            while ((i = fis.read(bt)) != -1) {
                String temp = new String(bt, "GBK");
                //System.out.println(temp);
                src += temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //记得关闭流
            fis.close();
        }
        return cutCatlog(src, name);
    }

    //解析小说并且分章节
    public List<String> cutCatlog(String src,String name) {

        //匹配规则
        String pest = "(正文){0,1}(\\s|\\n)(第)([\\u4e00-\\u9fa5a-zA-Z0-9]{1,7})[章][^\\n]{1,35}(|\\n)";//[章节卷集部回]( )
        //替换规则
        String washpest = "(PS|ps)(.)*(|\\n)";
        //将小说内容中的PS全部替换为“”
        src = src.replaceAll(washpest, "");
        //list用来储存章节内容
        List<String> list = new ArrayList<>();
        List<String> namelist = new ArrayList<String>();
        //根据匹配规则将小说分为一章一章的，并存到list
        for (String s : src.split(pest)) {
            list.add(s);
        }
        //System.out.println("size" + src.split(pest).length);
        //java正则匹配
        Pattern p = Pattern.compile(pest);
        Matcher m = p.matcher(src);
        int i = 1, j = 1;
        //存拼接章节内容和章节名后的内容
        List<String> newlist = new ArrayList<>();
        //临时字符串
        String newstr = null;
        //循环匹配
        while (m.find()) {
            newstr = "";
            //替换退格符
            String temp = m.group(0).replace(" ", "").replace("\r", "");
            if (i == list.size())
                break;
            //拼接章节名和内容
            newstr = temp + list.get(i);
            i++;
            newlist.add(newstr);
            //添加章节名在list,过滤干扰符号
            temp = temp.replaceAll("[（](.)*[）]", "").replace("：", "");
            temp = temp.replace("\\", "").replace("/", "").replace("|", "");

            temp = temp.replace("?", "").replace("*", "").replaceAll("[(](.)*[)]", "");
            //System.out.println("j=" + j + " temp=" + temp + ".txt");
            j++;
            namelist.add(temp.replace("\n", ".txt"));
            temp = "";
        }

        //2.创建目录
        File file = new File("D:\\novel\\"+name+"\\");
        if (!file.exists()) {
            file.mkdir();
        }
        String filedir = file.getPath();
        //循环生成章节TXT文件
        for (i = 0; i < newlist.size(); i++) {
            //System.out.println("catname="+filedir+File.separator+namelist.get(i));
            //2.在目录下创建TXT文件
            StringBuffer ctl = new StringBuffer(namelist.get(i));
            ctl.delete(0,4);
            namelist.set(i, String.valueOf(ctl));
            String bloodbath = filedir + "\\" + ctl.append(".txt");


            File book = new File(bloodbath);

            FileWriter fr = null;
            try {
                fr = new FileWriter(book);
                fr.write(newlist.get(i));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
        return namelist;
    }



}
