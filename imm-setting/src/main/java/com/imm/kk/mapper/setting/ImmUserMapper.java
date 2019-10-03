package com.imm.kk.mapper.setting;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.imm.kk.entity.setting.ImmUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @date 2019-05-13 19:39:21
 */
@Mapper
@Component
public interface ImmUserMapper extends BaseMapper<ImmUser> {

    List<ImmUser> selectAllData();

    int selectCnt();

}
