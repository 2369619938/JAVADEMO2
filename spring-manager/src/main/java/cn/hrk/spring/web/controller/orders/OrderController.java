package cn.hrk.spring.web.controller.orders;


import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.orders.domain.Order;
import cn.hrk.spring.orders.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderFeign OrderService;

    @GetMapping("/findAll1")
    public List<Order> findAl1(){
        return OrderService.findAll( );
    }
    @GetMapping("/findPage1")
    public PageResult<Order> findPage(int page, int size) {
        return OrderService. findPage(page,size) ;
    }
    @PostMapping("/findList1")
    public List<Order> findList(@RequestBody Map<String,Object> searchMap) {
        return OrderService. findList(searchMap) ;
    }
    @PostMapping("/findPage1" )
    public PageResult<Order> findPage(@RequestBody Map<String,Object> searchMap, int page, int size) {
        return OrderService.findPage(searchMap,page,size);
    }
    @GetMapping("/findById1/{id}")
    public Order findById(@PathVariable("id") Long id) {
        return OrderService.findById(id);
    }
    @PostMapping ("/add1" )
    public R add (@RequestBody Order Order) {
        OrderService.add(Order) ;
        return R.ok();
    }
    @PostMapping ("/update1" )
    public R update(@RequestBody Order Order) {
        OrderService.update(Order);
        return R.ok() ;
    }
    @GetMapping("/delete1/{id}")
    public R delete(@PathVariable("id") Long id) {
        OrderService.delete( id) ;
        return R.ok();
    }
    
}
