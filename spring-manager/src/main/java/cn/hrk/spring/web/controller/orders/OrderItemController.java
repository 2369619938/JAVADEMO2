package cn.hrk.spring.web.controller.orders;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.orders.domain.OrderItem;
import cn.hrk.spring.orders.feign.OrderItemFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController {
    @Autowired
    private OrderItemFeign OrderItemService;

    @GetMapping("/findAll1")
    public List<OrderItem> findAl1(){
        return OrderItemService.findAll( );
    }
    @GetMapping("/findPage1")
    public PageResult<OrderItem> findPage(int page, int size) {
        return OrderItemService. findPage(page,size) ;
    }
    @PostMapping("/findList1")
    public List<OrderItem> findList(@RequestBody Map<String,Object> searchMap) {
        return OrderItemService. findList(searchMap) ;
    }
    @PostMapping("/findPage1" )
    public PageResult<OrderItem> findPage(@RequestBody Map<String,Object> searchMap, int page, int size) {
        return OrderItemService.findPage(searchMap,page,size);
    }
    @GetMapping("/findById1/{id}")
    public OrderItem findById(@PathVariable("id") Long id) {
        return OrderItemService.findById(id);
    }
    @PostMapping ("/add1" )
    public R add (@RequestBody OrderItem OrderItem) {
        OrderItemService.add(OrderItem) ;
        return R.ok();
    }
    @PostMapping ("/update1" )
    public R update(@RequestBody OrderItem OrderItem) {
        OrderItemService.update(OrderItem);
        return R.ok() ;
    }
    @GetMapping("/delete1/{id}")
    public R delete(@PathVariable("id") Long id) {
        OrderItemService.delete( id) ;
        return R.ok();
    }
}
