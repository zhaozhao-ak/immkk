package com.imm.kk.action.setting;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping("say")
    public String sayHello(){
        return "Hello Spring Boot";
    }
}
