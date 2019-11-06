package cn.hrk.spring.goods.service;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.goods.domain.Para;
import cn.hrk.spring.goods.domain.Category;
import cn.hrk.spring.goods.domain.Para;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RequestMapping("/para")
public interface IParaService {
    @GetMapping("/findAll")
    public List<Para> findAll();
    @GetMapping("/findPage")
    public PageResult<Para> findPage(@RequestParam("page") int page, @RequestParam("size") int size);
    @PostMapping("/findList")
    public List<Para> findList(Map<String, Object> searchMap);
    @PostMapping("/findPage")
    public PageResult<Para> findPage(Map<String, Object> searchMap, @RequestParam("page") int page, @RequestParam("size") int size);
    @GetMapping("/findById/{id}")
    public Para findById(@PathVariable("id") Integer id);
    @PostMapping("/add")
    public void add(@RequestBody Para Para);
    @PostMapping("/update")
    public void update(@RequestBody Para Para);
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id);
}
