package com.imm.kk.entity.setting;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


/**
 * @author rjyx_huxinsheng
 * @date 2019-05-13 19:39:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "hdis_age")
@Alias(value = "hdisAge")
public class HdisAge{
    @TableId(value = "ageId", type = IdType.UUID)
    private String ageId;
    @TableField(value = "endAge")
    private Integer endAge;
    @TableField(value = "startAge")
    private Integer startAge;
    @TableField(value = "seq")
    private Integer seq;
    @TableField(value = "institutionCode", strategy = FieldStrategy.NOT_EMPTY)
    private String institutionCode;
    /**
     * 创建人
     */
    @TableField(value = "creator", strategy = FieldStrategy.NOT_EMPTY)
    private String creator;
    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @TableField(exist = false)
    List<HdisAge> datas;
}
