package com.leyou.item.service;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;

import java.util.List;

/**
 * @Author xmz
 * @Description BrandService
 * @Date 2021/9/14 17:09
 **/
public interface BrandService {

    /**
     * @Author xmz
     * @Description 分页查询品牌
     * @Date 2021/9/17 14:21
     **/
    public PageResult<Brand> queryBrandByPageAndSort(Integer page, Integer rows, String sortBy, Boolean desc, String key);

    /**
     * @Author xmz
     * @Description 添加品牌
     * @Date 2021/9/17 14:40
     **/
    public void saveBrand(Brand brand, List<Long> cids);

    public List<Brand> queryBrandsByCategoryId(Long cid);

    public List<Brand> queryBrandByIds(List<Long> ids);

    /**
     * @Author xmz
     * @Description 修改品牌
     * @Date 2021/9/27 15:57
     **/
    public boolean updateBand(Brand brand, List<Long> cids);

    public boolean deleteBrand(Long id);
}
