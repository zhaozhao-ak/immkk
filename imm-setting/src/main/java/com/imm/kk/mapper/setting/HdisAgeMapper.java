package com.imm.kk.mapper.setting;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.imm.kk.entity.setting.HdisAge;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @date 2019-05-13 19:39:21
 */
@Mapper
public interface HdisAgeMapper extends BaseMapper<HdisAge> {


    List<HdisAge> selectAllData();

    int selectCnt();

    HdisAge selectInfo();

}
