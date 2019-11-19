package cn.hrk.spring.activitys.feign;


import cn.hrk.common.constants.ServiceNameConstants;
import cn.hrk.spring.activitys.feign.Factory.AdFallbackFactory;
import cn.hrk.spring.activitys.service.IAdService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = ServiceNameConstants.SERVICE_ACTIVITY, url = "127.0.0.1:8004", fallbackFactory = AdFallbackFactory.class)
public interface AdFeign extends IAdService {

}

