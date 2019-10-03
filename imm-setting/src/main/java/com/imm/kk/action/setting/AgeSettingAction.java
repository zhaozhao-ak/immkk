package com.imm.kk.action.setting;

import com.imm.kk.result.HdisResult;
import com.imm.kk.service.setting.HdisAgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author SZ
 */
@RestController
@RequestMapping("setting/age")
public class AgeSettingAction {
    private final HdisAgeService hdisAgeService;

    @Autowired
    public AgeSettingAction(HdisAgeService hdisAgeService) {
        this.hdisAgeService = hdisAgeService;
    }

    @GetMapping("getAgeDate")
    @ResponseBody
    public HdisResult getMenuPermissionData() {
        return HdisResult.ok("dasadadasdad");
    }
}
