package cn.hrk.spring.activitys.feign;


import cn.hrk.common.constants.ServiceNameConstants;
import cn.hrk.spring.activitys.feign.Factory.ActivityFallbackFactory;
import cn.hrk.spring.activitys.service.IActivityService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(value = ServiceNameConstants.SERVICE_ACTIVITY, url = "127.0.0.1:8004", fallbackFactory = ActivityFallbackFactory.class)
public interface ActivityFeign extends IActivityService {

}

