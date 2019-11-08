package cn.hrk.spring.goods.feign;


import cn.hrk.common.constants.ServiceNameConstants;
import cn.hrk.spring.goods.feign.Factory.RemoteBrandFallbackFactory;
import cn.hrk.spring.goods.service.IBrandService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = ServiceNameConstants.SERVICE_GOODS, url = "127.0.0.1:8002", fallbackFactory = RemoteBrandFallbackFactory.class)
public interface BrandFeign extends IBrandService {

}

