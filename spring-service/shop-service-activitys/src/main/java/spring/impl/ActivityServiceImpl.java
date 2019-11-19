package spring.impl;

import cn.hrk.common.domain.IdWorker;
import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.activitys.domain.Activity;
import cn.hrk.spring.activitys.service.IActivityService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.mapper.ActivityMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/activity")
public class ActivityServiceImpl implements IActivityService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityServiceImpl.class);
    @Autowired
    private ActivityMapper activityMapper;
    IdWorker idWorker=new IdWorker();
    Date date=new Date();
    @GetMapping("/findAll")
    public List<Activity> findAll() {
        return activityMapper.selectAll();
    }
    /**
     * 分⻚查询
     * @param page ⻚码
     * @param size 每⻚记录数
     * @return 分⻚结果
     */
    @GetMapping("/findPage")
    public PageResult<Activity> findPage(@RequestParam("page") int page,
                                      @RequestParam("size") int size) {
        PageHelper.startPage(page,size);
        Page<Activity> Orders = (Page<Activity>) activityMapper.selectAll();
        return new PageResult<Activity>
                (Orders.getTotal(),Orders.getResult());
    }
    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @PostMapping("/findList")
    public List<Activity> findList(@RequestBody Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return activityMapper.selectByExample(example);
    }
    /**
     * 分⻚+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/findPage")
    public PageResult<Activity> findPage(@RequestBody Map<String, Object> searchMap,@RequestParam("page") int page,@RequestParam("size") int size)
    { PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Activity> Orders = (Page<Activity>)
                activityMapper.selectByExample(example);
        return new PageResult<Activity>
                (Orders.getTotal(),Orders.getResult()); }
    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public Activity findById(@PathVariable("id") Long id) {
        return activityMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param order
     */
    @PostMapping("/add")
    public void add(@RequestBody Activity order) {

//        order.setCreateTime(date);//创建⽇期
//        order.setId(idWorker.nextId()+"");
        activityMapper.insert(order);
    }

    /**
     * 修改
     * @param order
     */
    @PostMapping("/update")
    public void update(@RequestBody Activity order) {

        activityMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 删除
     * @param id
     */
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id)  {
        activityMapper.deleteByPrimaryKey(id);
    }


    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Activity.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 数量合计
            if (searchMap.get("totalNum") != null) {
                criteria.andEqualTo("totalNum",searchMap.get("totalNum"));
            }
        }
        return example;
    }

}
