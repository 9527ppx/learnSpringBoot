package com.wu.boot.config;

import com.wu.boot.util.EasySqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//开启mybatis事务管理
@EnableTransactionManagement
public class MPConfiguration {

    @Bean
    @Primary //批量插入配置
    public EasySqlInjector easySqlInjector(){
        return new EasySqlInjector();
    }
}
