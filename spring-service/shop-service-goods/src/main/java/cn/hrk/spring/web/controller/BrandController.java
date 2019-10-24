package cn.hrk.spring.web.controller;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.goods.domain.Brand;
import cn.hrk.spring.service.IBrandService;
import cn.hrk.spring.service.impl.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private IBrandService brandService;


    @GetMapping("/findAll")
    public List<Brand> findAl1(){
        return brandService.findAll( );
    }
    @GetMapping("/findPage")
    public PageResult<Brand> findPage(int page, int size) {
        return brandService. findPage(page,size) ;
    }
    @PostMapping("/findList")
    public List<Brand> findList(@RequestBody Map<String,Object>searchMap) {
        return brandService. findList(searchMap) ;
    }
    @PostMapping("/findPage" )
    public PageResult<Brand> findPage(@RequestBody Map<String, Object> searchMap, int page, int size) {
        return brandService.findPage(searchMap,page,size);
    }
    @GetMapping("/findById/{id}")
    public Brand findById(@PathVariable("id") Integer id) {
        return brandService.findById(id);
    }
    @PostMapping ("/add" )
    public R add (@RequestBody Brand brand) {
        brandService . add( brand) ;
        return R.ok();
    }
    @PostMapping ("/update" )
    public R update(@RequestBody Brand brand) {
        brandService.update(brand);
        return R.ok() ;
    }
    @GetMapping("/delete/{id}")
    public R delete(@PathVariable("id") Integer id) {
        brandService.delete( id) ;
        return R.ok();
    }

}
