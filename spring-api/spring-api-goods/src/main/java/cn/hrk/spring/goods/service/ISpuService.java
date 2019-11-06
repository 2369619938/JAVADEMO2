package cn.hrk.spring.goods.service;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.goods.domain.Spu;
import cn.hrk.spring.goods.domain.Goods;
import cn.hrk.spring.goods.domain.Spec;
import cn.hrk.spring.goods.domain.Spu;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RequestMapping("/spu")
public interface ISpuService {


    @GetMapping("/findAll")
    public List<Spu> findAll();
    @GetMapping("/findPage")
    public PageResult<Spu> findPage(@RequestParam("page") int page, @RequestParam("size") int size);
    @PostMapping("/findList")
    public List<Spu> findList(Map<String, Object> searchMap);
    @PostMapping("/findPage")
    public PageResult<Spu> findPage(Map<String, Object> searchMap, @RequestParam("page") int page, @RequestParam("size") int size);
    @GetMapping("/findById/{id}")
    public Spu findById(@PathVariable("id") Integer id);
    @PostMapping("/add")
    public void add(@RequestBody Spu Spu);
    @PostMapping("/update")
    public void update(@RequestBody Spu Spu);
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id);
    @PostMapping("/save")
    public void saveGoods(Goods goods);

}
