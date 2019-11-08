package cn.hrk.spring.goods.service;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Sku;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RequestMapping("/sku")
public interface ISkuService {

    @GetMapping("/findAll")
    public List<Sku> findAll();
    @GetMapping("/findPage")
    public PageResult<Sku> findPage(@RequestParam("page") int page, @RequestParam("size") int size);
    @PostMapping("/findList")
    public List<Sku> findList(Map<String, Object> searchMap);
    @PostMapping("/findPage")
    public PageResult<Sku> findPage(Map<String, Object> searchMap, @RequestParam("page") int page, @RequestParam("size") int size);
    @GetMapping("/findById/{id}")
    public Sku findById(@PathVariable("id") Integer id);
    @PostMapping("/add")
    public void add(@RequestBody Sku Sku);
    @PostMapping("/update")
    public void update(@RequestBody Sku Sku);
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id);
}
