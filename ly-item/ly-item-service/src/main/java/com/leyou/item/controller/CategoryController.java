package com.leyou.item.controller;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author xmz
 * @Description CategoryController
 * @Date 2021/9/13 15:21
 **/
@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * @Author xmz
     * @Description 根据pid查询分类，默认pid=0，即无父类
     * @Date 2021/9/17 11:55
     * **/
    @GetMapping("list")
    public ResponseEntity<List<Category>> findCategoryByPid(
       @RequestParam(value = "pid",defaultValue = "0") Long pid){

        List<Category> categoryList = categoryService.findCategoryByPid(pid);

        //没有查到返回404
        if (categoryList==null||categoryList.size()==0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(categoryList);
    }


    /**
     * @Author xmz
     * @Description 根据分类id，查询分类名
     * @Date 2021/10/17 15:16
     **/
    @GetMapping("names")
    public ResponseEntity<List<String>> queryCategoryNameById(@RequestParam("ids") List<Long> ids){
        List<String> nameList = this.categoryService.queryCategoryNameByCids(ids);
        if (nameList==null||nameList.size()==0){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(nameList);

    }


    /**
     * @Author xmz
     * @Description 通过品牌id查询分类
     * @Date 2021/9/18 17:21
     **/
    @GetMapping("/bid/{bid}")
    public ResponseEntity<List<Category>> queryByBrandId(@PathVariable("bid") Long bid){
        List<Category> list = this.categoryService.queryByBrandId(bid);
        if(list == null || list.size() < 1){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }
}
