package spring.mapper;

import cn.hrk.common.base.BaseMapper;
import cn.hrk.spring.activitys.domain.Ad;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AdMapper extends BaseMapper<Ad> {
}
