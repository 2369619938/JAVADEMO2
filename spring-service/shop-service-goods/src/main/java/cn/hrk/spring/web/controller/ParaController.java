package cn.hrk.spring.web.controller;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.goods.domain.Para;
import cn.hrk.spring.service.IParaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/para")
public class ParaController {
    @Autowired
    private IParaService paraService;

    @GetMapping("/findAll")
    public List<Para> findAl1(){
        return paraService.findAll( );
    }
    @GetMapping("/findPage")
    public PageResult<Para> findPage(int page, int size) {
        return paraService. findPage(page,size) ;
    }
    @PostMapping("/findList")
    public List<Para> findList(@RequestBody Map<String,Object> searchMap) {
        return paraService. findList(searchMap) ;
    }
    @PostMapping("/findPage" )
    public PageResult<Para> findPage(@RequestBody Map<String,Object> searchMap, int page, int size) {
        return paraService.findPage(searchMap,page,size);
    }
    @GetMapping("/findById/{id}")
    public Para findById(@PathVariable("id") Integer id) {
        return paraService.findById(id);
    }
    @PostMapping ("/add" )
    public R add (@RequestBody Para Para) {
        paraService.add(Para) ;
        return R.ok();
    }
    @PostMapping ("/update" )
    public R update(@RequestBody Para Para) {
        paraService.update(Para);
        return R.ok() ;
    }
    @GetMapping("/delete/{id}")
    public R delete(@PathVariable("id") Integer id) {
        paraService.delete( id) ;
        return R.ok();
    }
}
