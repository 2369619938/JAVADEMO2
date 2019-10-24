package cn.hrk.spring.web.controller;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.goods.domain.Spec;
import cn.hrk.spring.service.ISpecService;
import cn.hrk.spring.service.ISpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spec")
public class SpecController {
    @Autowired
    private ISpecService specService;

    @GetMapping("/findAll")
    public List<Spec> findAl1(){
        return specService.findAll( );
    }
    @GetMapping("/findPage")
    public PageResult<Spec> findPage(int page, int size) {
        return specService. findPage(page,size) ;
    }
    @PostMapping("/findList")
    public List<Spec> findList(@RequestBody Map<String,Object> searchMap) {
        return specService. findList(searchMap) ;
    }
    @PostMapping("/findPage" )
    public PageResult<Spec> findPage(@RequestBody Map<String,Object> searchMap, int page, int size) {
        return specService.findPage(searchMap,page,size);
    }
    @GetMapping("/findById/{id}")
    public Spec findById(@PathVariable("id") Integer id) {
        return specService.findById(id);
    }
    @PostMapping ("/add" )
    public R add (@RequestBody Spec Spec) {
        specService.add(Spec) ;
        return R.ok();
    }
    @PostMapping ("/update" )
    public R update(@RequestBody Spec Spec) {
        specService.update(Spec);
        return R.ok() ;
    }
    @GetMapping("/delete/{id}")
    public R delete(@PathVariable("id") Integer id) {
        specService.delete( id) ;
        return R.ok();
    }
    
}
