package cn.hrk.spring.web.controller;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.goods.domain.Pref;
import cn.hrk.spring.service.IPrefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pref")
public class PrefController {
    @Autowired
    private IPrefService prefService;

    @GetMapping("/findAll")
    public List<Pref> findAl1(){
        return prefService.findAll( );
    }
    @GetMapping("/findPage")
    public PageResult<Pref> findPage(int page, int size) {
        return prefService. findPage(page,size) ;
    }
    @PostMapping("/findList")
    public List<Pref> findList(@RequestBody Map<String,Object> searchMap) {
        return prefService. findList(searchMap) ;
    }
    @PostMapping("/findPage" )
    public PageResult<Pref> findPage(@RequestBody Map<String,Object> searchMap, int page, int size) {
        return prefService.findPage(searchMap,page,size);
    }
    @GetMapping("/findById/{id}")
    public Pref findById(@PathVariable("id") Integer id) {
        return prefService.findById(id);
    }
    @PostMapping ("/add" )
    public R add (@RequestBody Pref Pref) {
        prefService.add(Pref) ;
        return R.ok();
    }
    @PostMapping ("/update" )
    public R update(@RequestBody Pref Pref) {
        prefService.update(Pref);
        return R.ok() ;
    }
    @GetMapping("/delete/{id}")
    public R delete(@PathVariable("id") Integer id) {
        prefService.delete( id) ;
        return R.ok();
    }
}
