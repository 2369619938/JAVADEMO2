package cn.hrk.spring.web.controller;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.goods.domain.Spu;
import cn.hrk.spring.service.ISpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spu")
public class SpuController {
    @Autowired
    private ISpuService spuService;

    @GetMapping("/findAll")
    public List<Spu> findAl1(){
        return spuService.findAll( );
    }
    @GetMapping("/findPage")
    public PageResult<Spu> findPage(int page, int size) {
        return spuService. findPage(page,size) ;
    }
    @PostMapping("/findList")
    public List<Spu> findList(@RequestBody Map<String,Object> searchMap) {
        return spuService. findList(searchMap) ;
    }
    @PostMapping("/findPage" )
    public PageResult<Spu> findPage(@RequestBody Map<String,Object> searchMap, int page, int size) {
        return spuService.findPage(searchMap,page,size);
    }
    @GetMapping("/findById/{id}")
    public Spu findById(@PathVariable("id") Integer id) {
        return spuService.findById(id);
    }
    @PostMapping ("/add" )
    public R add (@RequestBody Spu Spu) {
        spuService.add(Spu) ;
        return R.ok();
    }
    @PostMapping ("/update" )
    public R update(@RequestBody Spu Spu) {
        spuService.update(Spu);
        return R.ok() ;
    }
    @GetMapping("/delete/{id}")
    public R delete(@PathVariable("id") Integer id) {
        spuService.delete( id) ;
        return R.ok();
    }
}
