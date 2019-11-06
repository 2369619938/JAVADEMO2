package cn.hrk.spring.feign;


import cn.hrk.common.constants.ServiceNameConstants;
import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Brand;
import cn.hrk.spring.goods.service.IBrandService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@FeignClient(value = ServiceNameConstants.SERVICE_GOODS, url = "127.0.0.1:8002", fallbackFactory = RemoteBrandFallbackFactory.class)
public interface BrandFeign extends IBrandService {

}

