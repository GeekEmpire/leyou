package com.leyou.item.mapper;

import com.leyou.item.pojo.Category;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author xmz
 * @Description CategoryMapper
 * @Date 2021/9/13 15:21
 **/
public interface CategoryMapper extends Mapper<Category>,SelectByIdListMapper<Category,Long> {

    /**
     * @Author xmz
     * @Description 根据品牌id查询商品分类
     * @Date 2021/9/18 17:26
     **/
    @Select("SELECT * FROM tb_category WHERE id IN (SELECT category_id FROM tb_category_brand WHERE brand_id = #{bid})")
    List<Category> queryByBrandId(Long bid);
}
