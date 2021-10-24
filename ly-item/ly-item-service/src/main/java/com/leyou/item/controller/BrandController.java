package com.leyou.item.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author ASUS
 * @Description
 * @Date 2021/9/14 16:50
 **/
@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * @Author xmz
     * @Description 在后端分页查询品牌
     * @Date 2021/9/17 11:54
     **/
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key) {

        PageResult<Brand> result = this.brandService.queryBrandByPageAndSort(page,rows,sortBy,desc, key);
        if (result == null || result.getItems().size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//NO_CONTENT
        }
        return ResponseEntity.ok(result);
    }

    /**
     * @Author xmz
     * @Description 添加品牌
     * @Date 2021/9/17 14:23
     **/
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids")List<Long> cids){
//        System.out.println(brand.getName());
        brandService.saveBrand(brand,cids);
        return new ResponseEntity<>(HttpStatus.CREATED);//201

    }

    /**
     * @Author xmz
     * @Description 修改品牌
     * @Date 2021/9/27 15:53
     **/
    @PutMapping
    public ResponseEntity<Void> updateBrand(Brand brand, @RequestParam("cids")List<Long> cids){
//        System.out.println(brand);
        boolean re = brandService.updateBand(brand,cids);
        if(re == false){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);//202
    }

    /**
     * @Author xmz
     * @Description 删除品牌
     * @Date 2021/10/17 15:15
     **/
    @DeleteMapping
    public ResponseEntity<Void> deleteBrand(@RequestParam("id")Long id){
        boolean re = brandService.deleteBrand(id);
        if(re == false){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);//202
    }

    /**
     * @Author xmz
     * @Description 通过分类id查询对应的品牌
     * @Date 2021/10/17 15:14
     **/
    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandsByCategoryId(@PathVariable("cid")Long cid){
        List<Brand> brandList = brandService.queryBrandsByCategoryId(cid);
        if (null==brandList||brandList.size()<1){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(brandList);
    }

    /**
     * 根据多个id查询品牌
     * @param ids
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Brand>> queryBrandByIds(@RequestParam("ids") List<Long> ids){
        List<Brand> list = this.brandService.queryBrandByIds(ids);
        if(list == null){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }
}
