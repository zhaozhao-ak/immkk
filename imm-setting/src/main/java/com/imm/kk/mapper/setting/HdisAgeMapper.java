package com.imm.kk.mapper.setting;

import com.imm.kk.entity.setting.HdisAge;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @date 2019-05-13 19:39:21
 */
@Mapper
@Repository
@Component
public interface HdisAgeMapper{


    List<HdisAge> selectAllData();

    int selectCnt();

    HdisAge selectInfo();

}
