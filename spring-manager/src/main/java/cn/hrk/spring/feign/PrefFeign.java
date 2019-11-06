package cn.hrk.spring.feign;


import cn.hrk.common.constants.ServiceNameConstants;
import cn.hrk.spring.goods.service.IBrandService;
import cn.hrk.spring.goods.service.IPrefService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = ServiceNameConstants.SERVICE_GOODS, url = "127.0.0.1:8002", fallbackFactory = RemotePrefFallbackFactory.class)
public interface PrefFeign extends IPrefService {

}

