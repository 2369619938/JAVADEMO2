package cn.hrk.spring.orders.feign;


import cn.hrk.common.constants.ServiceNameConstants;
import cn.hrk.spring.orders.feign.Factory.RemoteOrderFallbackFactory;
import cn.hrk.spring.orders.service.IOrderService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = ServiceNameConstants.SERVICE_ORDERS, url = "127.0.0.1:8003", fallbackFactory = RemoteOrderFallbackFactory.class)
public interface OrderFeign extends IOrderService {

}

