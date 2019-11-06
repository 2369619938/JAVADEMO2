package cn.hrk.spring.goods.service;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.goods.domain.Brand;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/brand")
public interface IBrandService {
    @GetMapping("/findAll")
    public List<Brand> findAll();
    @GetMapping("/findPage")
    public PageResult<Brand> findPage(@RequestParam("page") int page,@RequestParam("size") int size);
    @PostMapping("/findList")
    public List<Brand> findList(Map<String, Object> searchMap);
    @PostMapping("/findPage")
    public PageResult<Brand> findPage(Map<String, Object> searchMap, @RequestParam("page") int page, @RequestParam("size") int size);
    @GetMapping("/findById/{id}")
    public Brand findById(@PathVariable("id") Integer id);
    @PostMapping("/add")
    public R add(@RequestBody Brand brand);
    @PostMapping("/update")
    public R update(@RequestBody Brand brand);
    @GetMapping("/delete/{id}")
    public R delete(@PathVariable("id") Integer id);

}
