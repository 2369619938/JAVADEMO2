package cn.hrk.spring.goods.feign;


import cn.hrk.common.constants.ServiceNameConstants;
import cn.hrk.spring.goods.feign.Factory.RemoteSpecFallbackFactory;
import cn.hrk.spring.goods.service.ISpecService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = ServiceNameConstants.SERVICE_GOODS, url = "127.0.0.1:8002", fallbackFactory = RemoteSpecFallbackFactory.class)
public interface SpecFeign extends ISpecService {

}

