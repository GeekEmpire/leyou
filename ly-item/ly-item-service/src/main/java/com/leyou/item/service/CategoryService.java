package com.leyou.item.service;

import com.leyou.item.pojo.Category;

import java.util.List;

/**
 * @Description CategoryService
 * @Author xmz
 * @Date 16:53
 **/
public interface CategoryService {
    /**
     * @Author xmz
     * @Description 根据pid查询分类
     * @Date 2021/9/17 14:25
     **/
    public List<Category> findCategoryByPid(Long pid);

    public List<String> queryCategoryNameByCids(List<Long> cids);

    List<Category> queryByBrandId(Long bid);
}
