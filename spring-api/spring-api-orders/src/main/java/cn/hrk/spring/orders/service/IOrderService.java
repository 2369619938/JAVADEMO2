package cn.hrk.spring.orders.service;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.orders.domain.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/order")
public interface IOrderService {
    @GetMapping("/findAll")
    public List<Order> findAll();
    @GetMapping("/findPage")
    public PageResult<Order> findPage(@RequestParam("page") int page, @RequestParam("size") int size);
    @PostMapping("/findList")
    public List<Order> findList(@RequestBody Map<String, Object> searchMap);
    @PostMapping("/findPage")
    public PageResult<Order> findPage(@RequestBody Map<String, Object> searchMap, @RequestParam("page") int page, @RequestParam("size") int size);
    @GetMapping("/findById/{id}")
    public Order findById(@PathVariable("id") Long id);
    @PostMapping("/add")
    public void add(@RequestBody Order order);
    @PostMapping("/update")
    public void update(@RequestBody Order order);
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id);
}
