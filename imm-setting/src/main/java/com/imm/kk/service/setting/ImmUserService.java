package com.imm.kk.service.setting;


import com.imm.kk.entity.setting.ImmUser;
import com.imm.kk.mapper.setting.ImmUserMapper;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class ImmUserService {

    private Logger logger = LoggerFactory.getLogger(ImmUserService.class);

    private final ImmUserMapper immUserMapper;

    @Autowired
    public ImmUserService(ImmUserMapper immUserMapper) {
        this.immUserMapper = immUserMapper;
    }

    /**
     * 查询所有用户
     */
    public List<ImmUser> selectUsers() {
        return immUserMapper.selectUserList();
    }
}
