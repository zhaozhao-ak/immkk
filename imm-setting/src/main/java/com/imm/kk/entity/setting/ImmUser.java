package com.imm.kk.entity.setting;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;


/**
 * @author sz
 * @date 2019-05-13 19:39:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "imm_user")
@Alias(value = "ImmUser")
public class ImmUser {

    @TableId(value = "userId", type = IdType.UUID)
    private String userId;
    @TableField(value = "userName")
    private String userName;
}
