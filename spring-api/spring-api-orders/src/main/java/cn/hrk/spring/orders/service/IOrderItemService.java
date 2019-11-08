package cn.hrk.spring.orders.service;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.orders.domain.OrderItem;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/orderItem")
public interface IOrderItemService {
    @GetMapping("/findAll")
    public List<OrderItem> findAll();
    @GetMapping("/findPage")
    public PageResult<OrderItem> findPage(@RequestParam("page") int page, @RequestParam("size") int size);
    @PostMapping("/findList")
    public List<OrderItem> findList(@RequestBody Map<String, Object> searchMap);
    @PostMapping("/findPage")
    public PageResult<OrderItem> findPage(@RequestBody Map<String, Object> searchMap, @RequestParam("page") int page, @RequestParam("size") int size);
    @GetMapping("/findById/{id}")
    public OrderItem findById(@PathVariable("id") Long id);
    @PostMapping("/add")
    public void add(@RequestBody OrderItem orderItem);
    @PostMapping("/update")
    public void update(@RequestBody OrderItem orderItem);
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id);

}
