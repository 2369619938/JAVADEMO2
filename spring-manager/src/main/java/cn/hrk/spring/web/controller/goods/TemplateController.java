package cn.hrk.spring.web.controller.goods;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.feign.TemplateFeign;
import cn.hrk.spring.goods.domain.Template;
import cn.hrk.spring.goods.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/template")
public class TemplateController {
    @Autowired
    private TemplateFeign TemplateService;

    @GetMapping("/findAll1")
    public List<Template> findAl1(){
        return TemplateService.findAll( );
    }
    @GetMapping("/findPage1")
    public PageResult<Template> findPage(int page, int size) {
        return TemplateService. findPage(page,size) ;
    }
    @PostMapping("/findList1")
    public List<Template> findList(@RequestBody Map<String,Object> searchMap) {
        return TemplateService. findList(searchMap) ;
    }
    @PostMapping("/findPage1" )
    public PageResult<Template> findPage(@RequestBody Map<String,Object> searchMap, int page, int size) {
        return TemplateService.findPage(searchMap,page,size);
    }
    @GetMapping("/findById1/{id}")
    public Template findById(@PathVariable("id") Integer id) {
        return TemplateService.findById(id);
    }
    @PostMapping ("/add1" )
    public R add (@RequestBody Template Template) {
        TemplateService.add(Template) ;
        return R.ok();
    }
    @PostMapping ("/update1" )
    public R update(@RequestBody Template Template) {
        TemplateService.update(Template);
        return R.ok() ;
    }
    @GetMapping("/delete1/{id}")
    public R delete(@PathVariable("id") Integer id) {
        TemplateService.delete( id) ;
        return R.ok();
    }
}
