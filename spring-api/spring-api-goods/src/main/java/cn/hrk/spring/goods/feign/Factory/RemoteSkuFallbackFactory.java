package cn.hrk.spring.goods.feign.Factory;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Sku;
import cn.hrk.spring.goods.feign.SkuFeign;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class RemoteSkuFallbackFactory implements FallbackFactory<SkuFeign>
{
    @Override
    public SkuFeign create(Throwable throwable)
    {
    log.error(throwable.getMessage());
    return new SkuFeign() {
        @Override
        public List<Sku> findAll() {
            log.info("查询全部品牌失败，请重试！");
            return null;
        }

        @Override
        public PageResult<Sku> findPage(int page, int size) {
            log.info("分⻚查询品牌失败，请重试！");
            return null;
        }

        @Override
        public List<Sku> findList(Map<String, Object> searchMap) {
            log.info("条件查询品牌失败，请重试！");
            return null;
        }

        @Override
        public PageResult<Sku> findPage(Map<String, Object>searchMap, int page, int size) {
            log.info("分⻚条件查询品牌失败，请重试！");
            return null;
        }

        @Override
        public Sku findById(Integer id) {
            log.info("查询指定ID品牌失败，请重试！");
            return null;
        }

        @Override
        public void add(Sku Sku) {
            log.info("品牌添加失败，请重试！");

        }

        @Override
        public void update(Sku Sku) {
            log.info("品牌修改失败，请重试！");

        }

        @Override
        public void delete(Integer id) {
            log.info("品牌删除失败，请重试！");

        }
    };
    }
}
