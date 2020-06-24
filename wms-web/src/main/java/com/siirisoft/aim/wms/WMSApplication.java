package com.siirisoft.aim.wms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @User DKY
 * @Date 2020/6/4
 * @Description wms启动类
 */
@EnableTransactionManagement
@SpringBootApplication
@EnableFeignClients
@MapperScan({"com.siirisoft.aim.wms.mapper"})
public class WMSApplication {
    public static void main(String[] args) {
        SpringApplication.run(WMSApplication.class, args);
    }
}
