package com.imm.common.result;

import com.imm.common.page.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author HuXinsheng
 */
@Data
public class DataTableResult extends Result implements Serializable {

    private int pageSize = 10;
    private long totalPage;
    private int currPage = 1;
    private long total;
    private long iTotalRecords;
    private long iTotalDisplayRecords;

    public DataTableResult() {
        super();
    }

    private DataTableResult(Page page) {
        this.pageSize = page.getPageSize();
        this.totalPage = page.getTotalPage();
        this.currPage = page.getPageSize();
        this.total = page.getTotal();
        this.iTotalRecords = page.getTotal();
        this.iTotalDisplayRecords = page.getTotal();
    }


    public static DataTableResult ok(Page page, List<?> rows) {
        DataTableResult result = new DataTableResult(page);
        result.code = OK;
        result.rows = rows;
        return result;
    }

}
