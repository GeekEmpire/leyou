package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author xmz
 * @Description 品牌mapper
 * @Date 2021/9/14 16:49
 **/
public interface BrandMapper extends Mapper<Brand>,SelectByIdListMapper<Brand,Long> {

    /**
     * @Author xmz
     * @Description 新增商品分类和品牌中间表数据
     * @Date 2021/9/17 14:28
     **/
    @Insert("INSERT INTO tb_category_brand (category_id, brand_id) VALUES (#{cid},#{bid})")
    void insertCategoryAndBrand(@Param("cid") Long cid, @Param("bid") Long bid);

    @Select("SELECT b.* FROM tb_brand b LEFT JOIN tb_category_brand cb ON b.id = cb.brand_id WHERE cb.category_id=#{cid}")
    List<Brand> queryBrandsByCategoryId(Long cid);

    @Insert("DELETE FROM tb_category_brand WHERE brand_id = #{bid}")
    void deleteCategoryAndBrand(@Param("bid")Long bid);
}
