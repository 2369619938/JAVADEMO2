package cn.hrk.spring.web.controller.Activitys;


import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.activitys.domain.Activity;
import cn.hrk.spring.activitys.feign.ActivityFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityFeign ActivityService;

    @GetMapping("/findAll1")
    public List<Activity> findAl1(){
        return ActivityService.findAll( );
    }
    @GetMapping("/findPage1")
    public PageResult<Activity> findPage(int page, int size) {
        return ActivityService. findPage(page,size) ;
    }
    @PostMapping("/findList1")
    public List<Activity> findList(@RequestBody Map<String,Object> searchMap) {
        return ActivityService. findList(searchMap) ;
    }
    @PostMapping("/findPage1" )
    public PageResult<Activity> findPage(@RequestBody Map<String,Object> searchMap, int page, int size) {
        return ActivityService.findPage(searchMap,page,size);
    }
    @GetMapping("/findById1/{id}")
    public Activity findById(@PathVariable("id") Long id) {
        return ActivityService.findById(id);
    }
    @PostMapping ("/add1" )
    public R add (@RequestBody Activity Activity) {
        ActivityService.add(Activity) ;
        return R.ok();
    }
    @PostMapping ("/update1" )
    public R update(@RequestBody Activity Activity) {
        ActivityService.update(Activity);
        return R.ok() ;
    }
    @GetMapping("/delete1/{id}")
    public R delete(@PathVariable("id") Long id) {
        ActivityService.delete( id) ;
        return R.ok();
    }
    
}
