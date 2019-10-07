package com.imm.kk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author imm-sz
 */
@SpringBootApplication
@ComponentScan(value = {"com.imm.kk"})
public class ImmkkAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImmkkAdminApplication.class, args);
    }
}
