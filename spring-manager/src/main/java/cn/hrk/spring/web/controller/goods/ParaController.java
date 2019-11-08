package cn.hrk.spring.web.controller.goods;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.goods.feign.ParaFeign;
import cn.hrk.spring.goods.domain.Para;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/para")
public class ParaController {
    @Autowired
    private ParaFeign paraService;

    @GetMapping("/findAll1")
    public List<Para> findAl1(){
        return paraService.findAll( );
    }
    @GetMapping("/findPage1")
    public PageResult<Para> findPage(int page, int size) {
        return paraService. findPage(page,size) ;
    }
    @PostMapping("/findList1")
    public List<Para> findList(@RequestBody Map<String,Object> searchMap) {
        return paraService. findList(searchMap) ;
    }
    @PostMapping("/findPage1" )
    public PageResult<Para> findPage(@RequestBody Map<String,Object> searchMap, int page, int size) {
        return paraService.findPage(searchMap,page,size);
    }
    @GetMapping("/findById1/{id}")
    public Para findById(@PathVariable("id") Integer id) {
        return paraService.findById(id);
    }
    @PostMapping ("/add1" )
    public R add (@RequestBody Para Para) {
        paraService.add(Para) ;
        return R.ok();
    }
    @PostMapping ("/update1" )
    public R update(@RequestBody Para Para) {
        paraService.update(Para);
        return R.ok() ;
    }
    @GetMapping("/delete1/{id}")
    public R delete(@PathVariable("id") Integer id) {
        paraService.delete( id) ;
        return R.ok();
    }
}
