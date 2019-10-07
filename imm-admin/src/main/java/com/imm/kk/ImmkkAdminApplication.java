package com.imm.kk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author imm-sz
 */
@SpringBootApplication
@MapperScan("com.imm.kk.mapper")
public class ImmkkAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImmkkAdminApplication.class, args);
    }
}
