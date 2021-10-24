package com.leyou.common.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author ASUS
 * @Description 分页实体
 * @Date 2021/9/14 16:51
 **/
@Data
public class PageResult<T> {
    private Long total;// 总条数
    private Long totalPage;// 总页数
    private List<T> items;// 当前页数据

    public PageResult() {
    }

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, Long totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }
}