package com.imm.kk.service.setting;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.imm.kk.entity.setting.HdisAge;
import com.imm.kk.mapper.setting.HdisAgeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

    /**
     * 按主键查询
     * @return List<HdisAge>
     */

    public int findAllData() {
        Wrapper<HdisAge> wrapper = new EntityWrapper<>(new HdisAge());
        int hdisAges = ageMapper.selectCount(wrapper);
        return hdisAges;
    }
}
