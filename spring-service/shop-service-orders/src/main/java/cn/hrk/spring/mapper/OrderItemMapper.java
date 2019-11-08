package cn.hrk.spring.mapper;

import cn.hrk.common.base.BaseMapper;
import cn.hrk.spring.orders.domain.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OrderItemMapper extends BaseMapper<OrderItem> {
}
