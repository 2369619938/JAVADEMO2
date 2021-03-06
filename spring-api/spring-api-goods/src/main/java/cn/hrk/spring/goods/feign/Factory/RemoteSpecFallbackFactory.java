package cn.hrk.spring.goods.feign.Factory;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Spec;
import cn.hrk.spring.goods.feign.SpecFeign;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class RemoteSpecFallbackFactory implements FallbackFactory<SpecFeign>
{
    @Override
    public SpecFeign create(Throwable throwable)
    {
    log.error(throwable.getMessage());
    return new SpecFeign() {
        @Override
        public List<Spec> findAll() {
            log.info("查询全部品牌失败，请重试！");
            return null;
        }

        @Override
        public PageResult<Spec> findPage(int page, int size) {
            log.info("分⻚查询品牌失败，请重试！");
            return null;
        }

        @Override
        public List<Spec> findList(Map<String, Object> searchMap) {
            log.info("条件查询品牌失败，请重试！");
            return null;
        }

        @Override
        public PageResult<Spec> findPage(Map<String, Object>searchMap, int page, int size) {
            log.info("分⻚条件查询品牌失败，请重试！");
            return null;
        }

        @Override
        public Spec findById(Integer id) {
            log.info("查询指定ID品牌失败，请重试！");
            return null;
        }

        @Override
        public void add(Spec Spec) {
            log.info("品牌添加失败，请重试！");

        }

        @Override
        public void update(Spec Spec) {
            log.info("品牌修改失败，请重试！");

        }

        @Override
        public void delete(Integer id) {
            log.info("品牌删除失败，请重试！");

        }
    };
    }
}
