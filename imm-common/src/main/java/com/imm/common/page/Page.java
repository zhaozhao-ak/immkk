package com.imm.common.page;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kevin
 */
@Data
public class Page implements Serializable {
    /**
     * 当前页
     */
    private int page = 1;

    /**
     * 页面大小
     */
    private int pageSize = 10;
    /**
     * 总页数
     */
    private long totalPage;
    /**
     * 总记录数
     */
    private long total;
    /**
     * 排序字段
     */
    private String field;
    /**
     * 排序方式asc,desc
     */
    private String order;

    private boolean sort;

    private Map<String, Object> condition = new HashMap<>();
    /**
     * 是否分页
     */
    private boolean isPagination = true;

    public int getCurrPage() {
        return (this.page - 1) * this.pageSize;
    }

    public boolean isSort() {
        return "asc".equalsIgnoreCase(order);
    }

    public Page() {
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
