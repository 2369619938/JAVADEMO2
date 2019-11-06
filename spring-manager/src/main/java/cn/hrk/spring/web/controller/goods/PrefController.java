package cn.hrk.spring.web.controller.goods;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.feign.PrefFeign;
import cn.hrk.spring.goods.domain.Pref;
import cn.hrk.spring.goods.service.IPrefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pref")
public class PrefController {
    @Autowired
    private PrefFeign prefService;

    @GetMapping("/findAll1")
    public List<Pref> findAl1(){
        return prefService.findAll( );
    }
    @GetMapping("/findPage1")
    public PageResult<Pref> findPage(int page, int size) {
        return prefService. findPage(page,size) ;
    }
    @PostMapping("/findList1")
    public List<Pref> findList(@RequestBody Map<String,Object> searchMap) {
        return prefService. findList(searchMap) ;
    }
    @PostMapping("/findPage1" )
    public PageResult<Pref> findPage(@RequestBody Map<String,Object> searchMap, int page, int size) {
        return prefService.findPage(searchMap,page,size);
    }
    @GetMapping("/findById1/{id}")
    public Pref findById(@PathVariable("id") Integer id) {
        return prefService.findById(id);
    }
    @PostMapping ("/add1" )
    public R add (@RequestBody Pref Pref) {
        prefService.add(Pref) ;
        return R.ok();
    }
    @PostMapping ("/update1" )
    public R update(@RequestBody Pref Pref) {
        prefService.update(Pref);
        return R.ok() ;
    }
    @GetMapping("/delete1/{id}")
    public R delete(@PathVariable("id") Integer id) {
        prefService.delete( id) ;
        return R.ok();
    }
}
