package com.imm.common.page;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author kevin
 */
@Data
public class PageWrapper<T> implements Serializable {
    private Page page;
    private List<T> rows;

    public Page getPage() {
        return this.page;
    }

    public PageWrapper() {
    }

    public PageWrapper(Page page) {
        this.page = page;
    }

}