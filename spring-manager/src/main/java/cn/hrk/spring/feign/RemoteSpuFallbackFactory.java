package cn.hrk.spring.feign;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.goods.domain.Goods;
import cn.hrk.spring.goods.domain.Spu;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class RemoteSpuFallbackFactory implements FallbackFactory<SpuFeign>
{
    @Override
    public SpuFeign create(Throwable throwable)
    {
    log.error(throwable.getMessage());
    return new SpuFeign() {
        @Override
        public List<Spu> findAll() {
            log.info("查询全部品牌失败，请重试！");
            return null;
        }

        @Override
        public PageResult<Spu> findPage(int page, int size) {
            log.info("分⻚查询品牌失败，请重试！");
            return null;
        }

        @Override
        public List<Spu> findList(Map<String, Object> searchMap) {
            log.info("条件查询品牌失败，请重试！");
            return null;
        }

        @Override
        public PageResult<Spu> findPage(Map<String, Object>searchMap, int page, int size) {
            log.info("分⻚条件查询品牌失败，请重试！");
            return null;
        }

        @Override
        public Spu findById(Integer id) {
            log.info("查询指定ID品牌失败，请重试！");
            return null;
        }

        @Override
        public void add(Spu Spu) {
            log.info("品牌添加失败，请重试！");

        }

        @Override
        public void update(Spu Spu) {
            log.info("品牌修改失败，请重试！");

        }

        @Override
        public void delete(Integer id) {
            log.info("品牌删除失败，请重试！");

        }

        @Override
        public void saveGoods(Goods goods) {
            log.info("商品添加失败，请重试！");
        }
    };
    }
}
