package cn.hrk.spring.feign;

import cn.hrk.common.domain.R;
import cn.hrk.spring.goods.domain.Brand;
import cn.hrk.common.domain.PageResult;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class RemoteBrandFallbackFactory implements FallbackFactory<BrandFeign>
{
    @Override
    public BrandFeign create(Throwable throwable)
    {
    log.error(throwable.getMessage());
    return new BrandFeign() {
        @Override
        public List<Brand> findAll() {
            log.info("查询全部品牌失败，请重试！");
            return null;
        }

        @Override
        public PageResult<Brand> findPage(int page, int size) {
            log.info("分⻚查询品牌失败，请重试！");
            return null;
        }

        @Override
        public List<Brand> findList(Map<String, Object> searchMap) {
            log.info("条件查询品牌失败，请重试！");
            return null;
        }

        @Override
        public PageResult<Brand> findPage(Map<String, Object>searchMap, int page, int size) {
            log.info("分⻚条件查询品牌失败，请重试！");
            return null;
        }

        @Override
        public Brand findById(Integer id) {
            log.info("查询指定ID品牌失败，请重试！");
            return null;
        }

        @Override
        public R add(Brand brand) {
            log.info("品牌添加失败，请重试！");
            return null;
        }

        @Override
        public R update(Brand brand) {
            log.info("品牌修改失败，请重试！");
            return null;
        }

        @Override
        public R delete(Integer id) {
            log.info("品牌删除失败，请重试！");
            return null;
        }
    };
    }
}
