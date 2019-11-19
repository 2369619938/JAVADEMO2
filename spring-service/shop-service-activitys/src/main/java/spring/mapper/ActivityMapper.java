package spring.mapper;

import cn.hrk.common.base.BaseMapper;
import cn.hrk.spring.activitys.domain.Activity;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ActivityMapper extends BaseMapper<Activity> {
}
