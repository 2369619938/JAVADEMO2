package cn.hrk.spring.web.controller;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.goods.domain.Category;
import cn.hrk.spring.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/findAll")
    public List<Category> findAl1(){
        return categoryService.findAll( );
    }
    @GetMapping("/findPage")
    public PageResult<Category> findPage(int page, int size) {
        return categoryService. findPage(page,size) ;
    }
    @PostMapping("/findList")
    public List<Category> findList(@RequestBody Map<String,Object> searchMap) {
        return categoryService. findList(searchMap) ;
    }
    @PostMapping("/findPage" )
    public PageResult<Category> findPage(@RequestBody Map<String,Object> searchMap, int page, int size) {
        return categoryService.findPage(searchMap,page,size);
    }
    @GetMapping("/findById/{id}")
    public Category findById(@PathVariable("id") Integer id) {
        return categoryService.findById(id);
    }
    @PostMapping ("/add" )
    public R add (@RequestBody Category Category) {
        categoryService.add(Category) ;
        return R.ok();
    }
    @PostMapping ("/update" )
    public R update(@RequestBody Category Category) {
        categoryService.update(Category);
        return R.ok() ;
    }
    @GetMapping("/delete/{id}")
    public R delete(@PathVariable("id") Integer id) {
        categoryService.delete( id) ;
        return R.ok();
    }
}
