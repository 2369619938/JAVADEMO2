package cn.hrk.spring.goods.service;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RequestMapping("/category")
public interface ICategoryService {
    @GetMapping("/findAll")
    public List<Category> findAll();
    @GetMapping("/findPage")
    public PageResult<Category> findPage(@RequestParam("page") int page, @RequestParam("size") int size);
    @PostMapping("/findList")
    public List<Category> findList(Map<String, Object> searchMap);
    @PostMapping("/findPage")
    public PageResult<Category> findPage(Map<String, Object> searchMap, @RequestParam("page") int page, @RequestParam("size") int size);
    @GetMapping("/findById/{id}")
    public Category findById(@PathVariable("id") Integer id);
    @PostMapping("/add")
    public void add(@RequestBody Category Category);
    @PostMapping("/update")
    public void update(@RequestBody Category Category);
    @GetMapping("/delete/{id}")
    public int delete(@PathVariable("id") Integer id);
    @RequestMapping("/findCategoryTree")
    public List<Map> findCategoryTree();
}
