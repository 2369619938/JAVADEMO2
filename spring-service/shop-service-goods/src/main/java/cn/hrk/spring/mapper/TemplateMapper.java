package cn.hrk.spring.mapper;

import cn.hrk.common.base.BaseMapper;
import cn.hrk.spring.goods.domain.Template;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TemplateMapper extends BaseMapper<Template> {
}
