package cn.hrk.spring.web.controller.goods;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.feign.CategoryFeign;
import cn.hrk.spring.goods.domain.Category;
import cn.hrk.spring.goods.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryFeign categoryService;

    @GetMapping("/findAll1")
    public List<Category> findAl1(){
        return categoryService.findAll( );
    }
    @GetMapping("/findPage1")
    public PageResult<Category> findPage(int page, int size) {
        return categoryService. findPage(page,size) ;
    }
    @PostMapping("/findList1")
    public List<Category> findList(@RequestBody Map<String,Object> searchMap) {
        return categoryService. findList(searchMap) ;
    }
    @PostMapping("/findPage1" )
    public PageResult<Category> findPage(@RequestBody Map<String,Object> searchMap, int page, int size) {
        return categoryService.findPage(searchMap,page,size);
    }
    @GetMapping("/findById1/{id}")
    public Category findById(@PathVariable("id") Integer id) {
        return categoryService.findById(id);
    }
    @PostMapping ("/add1" )
    public R add (@RequestBody Category Category) {
        categoryService.add(Category) ;
        return R.ok();
    }
    @PostMapping ("/update1" )
    public R update(@RequestBody Category Category) {
        categoryService.update(Category);
        return R.ok() ;
    }
    @GetMapping("/delete1/{id}")
    public R delete(@PathVariable("id") Integer id) {
       if (categoryService.delete( id)>0){
        return R.ok();
       }
       return R.error(400,"存在子分类");
    }
}
