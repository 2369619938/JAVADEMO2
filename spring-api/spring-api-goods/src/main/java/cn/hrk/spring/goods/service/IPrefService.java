package cn.hrk.spring.goods.service;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.goods.domain.Pref;
import cn.hrk.spring.goods.domain.Para;
import cn.hrk.spring.goods.domain.Pref;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RequestMapping("/pref")
public interface IPrefService {
    @GetMapping("/findAll")
    public List<Pref> findAll();
    @GetMapping("/findPage")
    public PageResult<Pref> findPage(@RequestParam("page") int page, @RequestParam("size") int size);
    @PostMapping("/findList")
    public List<Pref> findList(Map<String, Object> searchMap);
    @PostMapping("/findPage")
    public PageResult<Pref> findPage(Map<String, Object> searchMap, @RequestParam("page") int page, @RequestParam("size") int size);
    @GetMapping("/findById/{id}")
    public Pref findById(@PathVariable("id") Integer id);
    @PostMapping("/add")
    public void add(@RequestBody Pref Pref);
    @PostMapping("/update")
    public void update(@RequestBody Pref Pref);
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id);
}
