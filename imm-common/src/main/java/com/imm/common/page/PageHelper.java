package com.imm.common.page;

import com.baomidou.mybatisplus.mapper.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author kevin
 */
public class PageHelper {

    public static final String FILTER_SQL = "filterSql";

    public PageHelper() {
    }

    public static <T> PageWrapper<T> getAllPageData(BaseMapper<T> baseMapper, Page page) {
        com.baomidou.mybatisplus.plugins.Page<T> dbPage = new com.baomidou.mybatisplus.plugins.Page<>();
        dbPage.setCurrent(page.getPage());
        dbPage.setSize(page.getPageSize());
        String s = null;
        if (!CollectionUtils.isEmpty(page.getCondition()) && page.getCondition().containsKey(FILTER_SQL)) {
            s = (String) page.getCondition().get(FILTER_SQL);
            page.getCondition().remove(FILTER_SQL);
        }
        dbPage.setCondition(page.getCondition());
        Wrapper wrapper = Condition.EMPTY;
        SqlHelper.fillWrapper(dbPage, wrapper);
        if (!StringUtils.isEmpty(page.getField())) {
            wrapper.orderBy(page.getField(), page.isSort());
        }
        if (!StringUtils.isEmpty(s)) {
            wrapper.and(s);
        }
        dbPage.setRecords(baseMapper.selectPage(dbPage, wrapper));
        Page resPage = new Page();
        resPage.setPage(dbPage.getCurrent());
        resPage.setPageSize(dbPage.getSize());
        resPage.setTotal(dbPage.getTotal());
        resPage.setTotalPage(dbPage.getPages());
        PageWrapper<T> paginationBean = new PageWrapper<>(resPage);
        paginationBean.setRows(dbPage.getRecords());
        return paginationBean;
    }

    public static <T> PageWrapper<T> getPageData(BaseMapper<T> baseMapper, Page page, T paramter) {
        com.baomidou.mybatisplus.plugins.Page<T> dbPage = new com.baomidou.mybatisplus.plugins.Page<>();
        dbPage.setCurrent(page.getPage());
        dbPage.setSize(page.getPageSize());
        String s = null;
        if (!CollectionUtils.isEmpty(page.getCondition()) && page.getCondition().containsKey(FILTER_SQL)) {
            s = (String) page.getCondition().get(FILTER_SQL);
            page.getCondition().remove(FILTER_SQL);
        }
        dbPage.setCondition(page.getCondition());
        Wrapper wrapper = new EntityWrapper<>(paramter);
        SqlHelper.fillWrapper(dbPage, wrapper);
        if (!StringUtils.isEmpty(page.getField())) {
            wrapper.orderBy(page.getField(), page.isSort());
        }
        if (!StringUtils.isEmpty(s)) {
            wrapper.and(s);
        }
        dbPage.setRecords(baseMapper.selectPage(dbPage, wrapper));
        Page resPage = new Page();
        resPage.setPage(dbPage.getCurrent());
        resPage.setPageSize(dbPage.getSize());
        resPage.setTotal(dbPage.getTotal());
        resPage.setTotalPage(dbPage.getPages());
        PageWrapper<T> paginationBean = new PageWrapper<>(resPage);
        paginationBean.setRows(dbPage.getRecords());
        return paginationBean;
    }

    public static <T> PageWrapper<T> getPageData(BaseMapper<T> baseMapper, Page page, Wrapper wrapper) {
        com.baomidou.mybatisplus.plugins.Page<T> dbPage = new com.baomidou.mybatisplus.plugins.Page<>();
        dbPage.setCurrent(page.getPage());
        dbPage.setSize(page.getPageSize());
        String s = null;
        if (!CollectionUtils.isEmpty(page.getCondition()) && page.getCondition().containsKey(FILTER_SQL)) {
            s = (String) page.getCondition().get(FILTER_SQL);
            page.getCondition().remove(FILTER_SQL);
        }
        dbPage.setCondition(page.getCondition());
        SqlHelper.fillWrapper(dbPage, wrapper);
        if (!StringUtils.isEmpty(page.getField())) {
            wrapper.orderBy(page.getField(), page.isSort());
        }
        if (!StringUtils.isEmpty(s)) {
            wrapper.and(s);
        }
        dbPage.setRecords(baseMapper.selectPage(dbPage, wrapper));
        Page resPage = new Page();
        resPage.setPage(dbPage.getCurrent());
        resPage.setPageSize(dbPage.getSize());
        resPage.setTotal(dbPage.getTotal());
        resPage.setTotalPage(dbPage.getPages());
        PageWrapper<T> paginationBean = new PageWrapper<>(resPage);
        paginationBean.setRows(dbPage.getRecords());
        return paginationBean;
    }
}
