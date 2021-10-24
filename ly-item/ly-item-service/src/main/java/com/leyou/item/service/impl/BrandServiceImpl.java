package com.leyou.item.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author xmz
 * @Description
 * @Date 2021/9/14 16:50
 **/
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * @Author xmz
     * @Description 分页查询品牌
     * @Date 2021/9/17 14:22
     **/
    public PageResult<Brand> queryBrandByPageAndSort(Integer page, Integer rows, String sortBy, Boolean desc, String key) {

        //开启分页查询，page表示当前页，rows表示页容量
        PageHelper.startPage(page,rows);

        Example example = new Example(Brand.class);

        //包含排序字段
        if (StringUtils.isNotBlank(sortBy)){

            //根据排序的升序或者降序来设置排序的方式
            String orderClause = sortBy+ (desc? " DESC" : " ASC");

            example.setOrderByClause(orderClause);//id ASC
        }

        if(StringUtils.isNotBlank(key)){
            example.createCriteria().andLike("name","%"+key+"%").orEqualTo("letter",key.toUpperCase());
        }

        Page<Brand> brands = (Page<Brand>) brandMapper.selectByExample(example);

        PageResult<Brand> pageResult = new PageResult<>(brands.getTotal(),brands);

        return pageResult;
    }

    /**
     * @Author xmz
     * @Description 添加品牌
     * @Date 2021/9/17 14:23
     **/
    public void saveBrand(Brand brand, List<Long> cids) {
        //由通用mapper保存brand品牌
        brandMapper.insert(brand);

        for (Long cid : cids) {
            brandMapper.insertCategoryAndBrand(cid,brand.getId());
        }
    }

    public List<Brand> queryBrandsByCategoryId(Long cid) {
        return brandMapper.queryBrandsByCategoryId(cid);
    }

    public List<Brand> queryBrandByIds(List<Long> ids) {
        return this.brandMapper.selectByIdList(ids);
    }

    /**
     * @Author xmz
     * @Description updateBand
     * @Date 2021/9/27 16:05
     **/
    @Override
    public boolean updateBand(Brand brand, List<Long> cids) {
        //由通用mapper修改brand品牌
        try{
            brandMapper.updateByPrimaryKey(brand);
            for (Long cid : cids) {
                brandMapper.deleteCategoryAndBrand(brand.getId());
                brandMapper.insertCategoryAndBrand(cid,brand.getId());
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteBrand(Long id) {
        //由通用mapper删除brand品牌
        try{
            brandMapper.deleteByPrimaryKey(id);
            brandMapper.deleteCategoryAndBrand(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
