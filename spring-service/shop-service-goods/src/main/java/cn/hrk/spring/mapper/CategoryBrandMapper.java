package cn.hrk.spring.mapper;

import cn.hrk.common.base.BaseMapper;
import cn.hrk.spring.goods.domain.Brand;
import cn.hrk.spring.goods.domain.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CategoryBrandMapper extends BaseMapper<CategoryBrand> {
}