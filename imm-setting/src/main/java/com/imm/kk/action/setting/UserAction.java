package com.imm.kk.action.setting;

import com.imm.kk.result.HdisResult;
import com.imm.kk.service.setting.ImmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SZ
 */
@RestController
@RequestMapping("setting/user")
public class UserAction {
    private final ImmUserService immUserService;

    @Autowired
    public UserAction(ImmUserService immUserService) {
        this.immUserService = immUserService;
    }

    @GetMapping("test")
    @ResponseBody
    public HdisResult getTest() {
        return HdisResult.ok("test----user -----");
    }

    @GetMapping("getUserList")
    @ResponseBody
    public HdisResult getUsers() {
        return HdisResult.list(immUserService.selectUsers());
    }
    @GetMapping("addUser")
    @ResponseBody
    public HdisResult addUser() {
        immUserService.addUser();
        return HdisResult.ok();
    }


}
