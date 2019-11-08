package cn.hrk.spring.impl;

import cn.hrk.common.domain.IdWorker;
import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.mapper.OrderMapper;
import cn.hrk.spring.mapper.OrderMapper;
import cn.hrk.spring.orders.domain.Order;
import cn.hrk.spring.orders.service.IOrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderServiceImpl implements IOrderService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderMapper orderMapper;
    IdWorker idWorker=new IdWorker();
    Date date=new Date();
    @GetMapping("/findAll")
    public List<Order> findAll() {
        return orderMapper.selectAll();
    }
    /**
     * 分⻚查询
     * @param page ⻚码
     * @param size 每⻚记录数
     * @return 分⻚结果
     */
    @GetMapping("/findPage")
    public PageResult<Order> findPage(@RequestParam("page") int page,
                                      @RequestParam("size") int size) {
        PageHelper.startPage(page,size);
        Page<Order> Orders = (Page<Order>) orderMapper.selectAll();
        return new PageResult<Order>
                (Orders.getTotal(),Orders.getResult());
    }
    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @PostMapping("/findList")
    public List<Order> findList(@RequestBody Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return orderMapper.selectByExample(example);
    }
    /**
     * 分⻚+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/findPage")
    public PageResult<Order> findPage(@RequestBody Map<String, Object> searchMap,@RequestParam("page") int page,@RequestParam("size") int size)
    { PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Order> Orders = (Page<Order>)
                orderMapper.selectByExample(example);
        return new PageResult<Order>
                (Orders.getTotal(),Orders.getResult()); }
    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public Order findById(@PathVariable("id") Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param order
     */
    @PostMapping("/add")
    public void add(@RequestBody Order order) {

        order.setCreateTime(date);//创建⽇期
        order.setId(idWorker.nextId()+"");
        orderMapper.insert(order);
    }

    /**
     * 修改
     * @param order
     */
    @PostMapping("/update")
    public void update(@RequestBody Order order) {

        orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 删除
     * @param id
     */
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id)  {
        orderMapper.deleteByPrimaryKey(id);
    }
    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){ // 品牌名称
            if(searchMap.get("name")!=null &&
                    !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            // 品牌图⽚地址
            if(searchMap.get("image")!=null &&
                    !"".equals(searchMap.get("image"))){
                criteria.andLike("image","%"+searchMap.get("image")+"%");
            }
            // 品牌的⾸字⺟
            if(searchMap.get("letter")!=null &&
                    !"".equals(searchMap.get("letter"))){

                criteria.andLike("letter","%"+searchMap.get("letter")+"%");
            }
            // 品牌id
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }
            // 排序
            if(searchMap.get("seq")!=null ){
                criteria.andEqualTo("seq",searchMap.get("seq"));
            }
        }
        return example;
    }

}
