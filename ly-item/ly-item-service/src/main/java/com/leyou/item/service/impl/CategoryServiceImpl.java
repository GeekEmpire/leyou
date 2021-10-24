package com.leyou.item.service.impl;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xmz
 * @Description CategoryService
 * @Date 2021/9/13 15:21
 **/
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * @Author xmz
     * @Description 根据pid查询分类
     * @Date 2021/9/17 14:25
     **/
    public List<Category> findCategoryByPid(Long pid) {
        Category category = new Category();
        //查询使用通用mapper，由于条件不是id主键所以要封装对象中
        category.setParentId(pid);

        return categoryMapper.select(category);
    }


    public List<String> queryCategoryNameByCids(List<Long> cids) {

        List<Category> categoryList = categoryMapper.selectByIdList(cids);

        List<String> names = new ArrayList<>();

        for (Category category : categoryList) {
            names.add(category.getName());
        }


        return names ;
    }

    @Override
    public List<Category> queryByBrandId(Long bid) {
        return this.categoryMapper.queryByBrandId(bid);
    }
}
