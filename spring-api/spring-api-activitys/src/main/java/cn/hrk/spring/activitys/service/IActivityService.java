package cn.hrk.spring.activitys.service;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.activitys.domain.Activity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/activity")
public interface IActivityService {
    @GetMapping("/findAll")
    public List<Activity> findAll();
    @GetMapping("/findPage")
    public PageResult<Activity> findPage(@RequestParam("page") int page, @RequestParam("size") int size);
    @PostMapping("/findList")
    public List<Activity> findList(@RequestBody Map<String, Object> searchMap);
    @PostMapping("/findPage")
    public PageResult<Activity> findPage(@RequestBody Map<String, Object> searchMap, @RequestParam("page") int page, @RequestParam("size") int size);
    @GetMapping("/findById/{id}")
    public Activity findById(@PathVariable("id") Long id);
    @PostMapping("/add")
    public void add(@RequestBody Activity Activity);
    @PostMapping("/update")
    public void update(@RequestBody Activity Activity);
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id);
}
