package cn.hrk.spring.goods.feign;


import cn.hrk.common.constants.ServiceNameConstants;
import cn.hrk.spring.goods.feign.Factory.RemoteCategoryFallbackFactory;
import cn.hrk.spring.goods.service.ICategoryService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = ServiceNameConstants.SERVICE_GOODS, url = "127.0.0.1:8002", fallbackFactory = RemoteCategoryFallbackFactory.class)
public interface CategoryFeign extends ICategoryService {

}

