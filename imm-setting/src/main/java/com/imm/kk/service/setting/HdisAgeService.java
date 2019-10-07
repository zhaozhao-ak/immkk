package com.imm.kk.service.setting;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.imm.kk.entity.setting.HdisAge;
import com.imm.kk.mapper.setting.HdisAgeMapper;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class HdisAgeService {

    private Logger logger = LoggerFactory.getLogger(HdisAgeService.class);

    private final HdisAgeMapper ageMapper;

    @Autowired
    public HdisAgeService(HdisAgeMapper ageMapper) {
        this.ageMapper = ageMapper;
    }
}
