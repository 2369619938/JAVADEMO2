package cn.hrk.spring.impl;

import cn.hrk.common.domain.IdWorker;
import cn.hrk.common.domain.PageResult;

import cn.hrk.spring.mapper.OrderItemMapper;
import cn.hrk.spring.mapper.OrderItemMapper;
import cn.hrk.spring.orders.domain.OrderItem;
import cn.hrk.spring.orders.service.IOrderItemService;
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
@RequestMapping("/orderItem")
public class OrderItemServiceImpl implements IOrderItemService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderItemServiceImpl.class);
    @Autowired
    private OrderItemMapper orderItemMapper;
    IdWorker idWorker=new IdWorker();
    Date date=new Date();
    @GetMapping("/findAll")
    public List<OrderItem> findAll() {
        return orderItemMapper.selectAll();
    }
    /**
     * 分⻚查询
     * @param page ⻚码
     * @param size 每⻚记录数
     * @return 分⻚结果
     */
    @GetMapping("/findPage")
    public PageResult<OrderItem> findPage(@RequestParam("page") int page,
                                      @RequestParam("size") int size) {
        PageHelper.startPage(page,size);
        Page<OrderItem> orderItems = (Page<OrderItem>) orderItemMapper.selectAll();
        return new PageResult<OrderItem>
                (orderItems.getTotal(),orderItems.getResult());
    }
    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @PostMapping("/findList")
    public List<OrderItem> findList(@RequestBody Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return orderItemMapper.selectByExample(example);
    }
    /**
     * 分⻚+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/findPage")
    public PageResult<OrderItem> findPage(@RequestBody Map<String, Object> searchMap, @RequestParam("page") int page, @RequestParam("size") int size)
    { PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<OrderItem> orderItems = (Page<OrderItem>)
                orderItemMapper.selectByExample(example);
        return new PageResult<OrderItem>
                (orderItems.getTotal(),orderItems.getResult()); }
    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public OrderItem findById(@PathVariable("id") Long id) {
        return orderItemMapper.selectByPrimaryKey(id);
    }




    /**
     * 新增
     * @param orderItems
     */
    @PostMapping("/add")
    public void add(@RequestBody OrderItem orderItems) {
        orderItems.setId(idWorker.nextId()+"");
        orderItemMapper.insert(orderItems);
    }

    /**
     * 修改
     * @param orderItems
     */
    @PostMapping("/update")
    public void update(@RequestBody OrderItem orderItems) {
        orderItemMapper.updateByPrimaryKeySelective(orderItems);
    }

    /**
     * 删除
     * @param id
     */
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id)  {
        orderItemMapper.deleteByPrimaryKey(id);
    }
    /*
     *构建查询条件
     *@param searchMap
     *@return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(OrderItem.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            //相册名称
            //相册图片地址
            if(searchMap. get("image") !=null && !"".equals(searchMap. get("image"))){
                criteria. andLike("image","%"+searchMap.get("image")+"%");
            }
            //品牌id
            if(searchMap.get("orderId")!=null ){
                criteria. andEqualTo("orderId", searchMap.get("orderId"));
            }
        }
        return example;
    }
}
