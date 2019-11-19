package cn.hrk.spring.activitys.service;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.activitys.domain.Ad;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/ad")
public interface IAdService {
    @GetMapping("/findAll")
    public List<Ad> findAll();
    @GetMapping("/findPage")
    public PageResult<Ad> findPage(@RequestParam("page") int page, @RequestParam("size") int size);
    @PostMapping("/findList")
    public List<Ad> findList(@RequestBody Map<String, Object> searchMap);
    @PostMapping("/findPage")
    public PageResult<Ad> findPage(@RequestBody Map<String, Object> searchMap, @RequestParam("page") int page, @RequestParam("size") int size);
    @GetMapping("/findById/{id}")
    public Ad findById(@PathVariable("id") Long id);
    @PostMapping("/add")
    public void add(@RequestBody Ad Ad);
    @PostMapping("/update")
    public void update(@RequestBody Ad Ad);
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id);
    @RequestMapping("/findByPosition")
    public List<Ad> findByPosition(@RequestParam("position") String position);

}
