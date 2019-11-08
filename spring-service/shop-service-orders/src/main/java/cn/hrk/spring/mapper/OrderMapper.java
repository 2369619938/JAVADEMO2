package cn.hrk.spring.mapper;

import cn.hrk.common.base.BaseMapper;

import cn.hrk.spring.orders.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OrderMapper extends BaseMapper<Order> {
}
