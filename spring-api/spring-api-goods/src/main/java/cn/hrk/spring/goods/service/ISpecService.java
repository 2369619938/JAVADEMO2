package cn.hrk.spring.goods.service;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.goods.domain.Spec;
import cn.hrk.spring.goods.domain.Sku;
import cn.hrk.spring.goods.domain.Spec;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RequestMapping("/spec")
public interface ISpecService {
    @GetMapping("/findAll")
    public List<Spec> findAll();
    @GetMapping("/findPage")
    public PageResult<Spec> findPage(@RequestParam("page") int page, @RequestParam("size") int size);
    @PostMapping("/findList")
    public List<Spec> findList(Map<String, Object> searchMap);
    @PostMapping("/findPage")
    public PageResult<Spec> findPage(Map<String, Object> searchMap, @RequestParam("page") int page, @RequestParam("size") int size);
    @GetMapping("/findById/{id}")
    public Spec findById(@PathVariable("id") Integer id);
    @PostMapping("/add")
    public void add(@RequestBody Spec Spec);
    @PostMapping("/update")
    public void update(@RequestBody Spec Spec);
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id);
}
