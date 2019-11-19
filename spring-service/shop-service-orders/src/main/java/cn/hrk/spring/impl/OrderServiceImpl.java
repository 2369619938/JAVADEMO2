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
        if(searchMap!=null){
            // 数量合计
            if (searchMap.get("totalNum") != null) {
                criteria.andEqualTo("totalNum",searchMap.get("totalNum"));
            }
            // 金额合计
            if (searchMap.get("totalMoney") != null) {
                criteria.andEqualTo("totalMoney",searchMap.get("totalMoney"));
            }
            // 优惠金额
            if (searchMap.get("preMoney") != null) {
                criteria.andEqualTo("preMoney",searchMap.get("preMoney"));
            }
            // 邮费
            if (searchMap.get("postFee") != null) {
                criteria.andEqualTo("postFee",searchMap.get("postFee"));
            }
            // 实付金额
            if (searchMap.get("payMoney") != null) {
                criteria.andEqualTo("payMoney",searchMap.get("payMoney"));
            }
            // 支付类型，1、在线支付、0 货到付款
            if (searchMap.get("payType") != null) {
                criteria.andEqualTo("payType",searchMap.get("payType"));
            }
            // 订单创建时间
            if (searchMap.get("createTime") != null && !"".equals(searchMap.get("createTime"))) {
                criteria.andLike("createTime", "%" + searchMap.get("createTime") + "%");
            }
            // 订单更新时间
            if (searchMap.get("updateTime") != null && !"".equals(searchMap.get("updateTime"))) {
                criteria.andLike("updateTime", "%" + searchMap.get("updateTime") + "%");
            }

            // 付款时间
            if (searchMap.get("payTime") != null && !"".equals(searchMap.get("payTime"))) {
                criteria.andLike("payTime", "%" + searchMap.get("payTime") + "%");
            }
            // 发货时间
            if (searchMap.get("consignTime") != null && !"".equals(searchMap.get("consignTime"))) {
                criteria.andLike("consignTime", "%" + searchMap.get("consignTime") + "%");
            }
            //交易完成时间
            if (searchMap.get("endTime") != null && !"".equals(searchMap.get("endTime"))) {
                criteria.andLike("endTime", "%" + searchMap.get("endTime") + "%");
            }
            // 交易关闭时间
            if (searchMap.get("closeTime") != null && !"".equals(searchMap.get("closeTime"))) {
                criteria.andLike("closeTime", "%" + searchMap.get("closeTime") + "%");
            }

            // 物流名称
            if (searchMap.get("shippingName") != null && !"".equals(searchMap.get("shippingName"))) {
                criteria.andLike("shippingName", "%" + searchMap.get("shippingName") + "%");
            }
            // 物流单号
            if (searchMap.get("shippingCode") != null) {
                criteria.andEqualTo("shippingCode",searchMap.get("shippingCode"));
            }
            // 用户名称
            if (searchMap.get("username") != null && !"".equals(searchMap.get("username"))) {
                criteria.andLike("username", "%" + searchMap.get("username") + "%");
            }
            // 买家留言
            if (searchMap.get("buyerMessage") != null && !"".equals(searchMap.get("buyerMessage"))) {
                criteria.andLike("buyerMessage", "%" + searchMap.get("buyerMessage") + "%");
            }
            // 是否评价
            if (searchMap.get("buyerRate") != null) {
                criteria.andEqualTo("buyerRate",searchMap.get("buyerRate"));
            }
            // 收货人
            if (searchMap.get("receiverContact") != null && !"".equals(searchMap.get("receiverContact"))) {
                criteria.andLike("receiverContact", "%" + searchMap.get("receiverContact") + "%");
            }
            // 收货人手机
            if (searchMap.get("receiverMobile") != null && !"".equals(searchMap.get("receiverMobile"))) {
                criteria.andLike("receiverMobile", "%" + searchMap.get("receiverMobile") + "%");
            }
            //收货人地址
            if (searchMap.get("receiverAddress") != null && !"".equals(searchMap.get("receiverAddress"))) {
                criteria.andLike("receiverAddress", "%" + searchMap.get("receiverAddress") + "%");
            }
            // 订单来源：1:web，2：app，3：微信公众号，4：微信小程序  5 H5手机页面
            if (searchMap.get("sourceType") != null) {
                criteria.andEqualTo("sourceType",searchMap.get("sourceType"));
            }
            // 交易流水号
            if (searchMap.get("transactionId") != null) {
                criteria.andEqualTo("transactionId",searchMap.get("transactionId"));
            }
            // 订单状态
            if (searchMap.get("orderStatus") != null) {
                criteria.andEqualTo("orderStatus",searchMap.get("orderStatus"));
            }
            // 支付状态
            if (searchMap.get("payStatus") != null) {
                criteria.andEqualTo("payStatus",searchMap.get("payStatus"));
            }
            // 发货状态
            if (searchMap.get("consignStatus") != null) {
                criteria.andEqualTo("consignStatus",searchMap.get("consignStatus"));
            }
            // 是否删除
            if (searchMap.get("isDelete") != null) {
                criteria.andEqualTo("isDelete",searchMap.get("isDelete"));
            }
        }
        return example;
    }

}
