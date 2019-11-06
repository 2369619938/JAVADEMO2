package cn.hrk.spring.goods.service;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.goods.domain.Template;
import cn.hrk.spring.goods.domain.Spu;
import cn.hrk.spring.goods.domain.Template;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RequestMapping("/template")
public interface ITemplateService {
    @GetMapping("/findAll")
    public List<Template> findAll();
    @GetMapping("/findPage")
    public PageResult<Template> findPage(@RequestParam("page") int page, @RequestParam("size") int size);
    @PostMapping("/findList")
    public List<Template> findList(Map<String, Object> searchMap);
    @PostMapping("/findPage")
    public PageResult<Template> findPage(Map<String, Object> searchMap, @RequestParam("page") int page, @RequestParam("size") int size);
    @GetMapping("/findById/{id}")
    public Template findById(@PathVariable("id") Integer id);
    @PostMapping("/add")
    public void add(@RequestBody Template Template);
    @PostMapping("/update")
    public void update(@RequestBody Template Template);
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id);
}
