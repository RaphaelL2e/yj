package com.agricultural;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//开启定时任务
@EnableScheduling
//开启缓存
@EnableCaching
@MapperScan("com.agricultural.dao")
@Import(FdfsClientConfig.class)
// 解决jmx重复注册bean的问题
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
//@EnableConfigurationProperties(DruidConfiguration.class)
public class YJWebsiteApplication {

    /**
     * Springboot整合Elasticsearch 在项目启动前设置一下的属性，防止报错
     * 解决netty冲突后初始化client时还会抛出异常
     * java.lang.IllegalStateException: availableProcessors is already set to [4], rejecting [4]
     */
    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(YJWebsiteApplication.class, args);

    }

}
