package cn.hrk.spring.feign;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.goods.domain.Album;
import cn.hrk.spring.goods.domain.Category;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class RemoteCategoryFallbackFactory implements FallbackFactory<CategoryFeign>
{
    @Override
    public CategoryFeign create(Throwable throwable)
    {
    log.error(throwable.getMessage());
    return new CategoryFeign() {
        @Override
        public List<Category> findAll() {
            log.info("查询全部品牌失败，请重试！");
            return null;
        }

        @Override
        public PageResult<Category> findPage(int page, int size) {
            log.info("分⻚查询品牌失败，请重试！");
            return null;
        }

        @Override
        public List<Category> findList(Map<String, Object> searchMap) {
            log.info("条件查询品牌失败，请重试！");
            return null;
        }

        @Override
        public PageResult<Category> findPage(Map<String, Object>searchMap, int page, int size) {
            log.info("分⻚条件查询品牌失败，请重试！");
            return null;
        }

        @Override
        public Category findById(Integer id) {
            log.info("查询指定ID品牌失败，请重试！");
            return null;
        }

        @Override
        public void add(Category category) {
            log.info("品牌添加失败，请重试！");

        }

        @Override
        public void update(Category category) {
            log.info("品牌修改失败，请重试！");

        }

        @Override
        public int delete(Integer id) {
            log.info("品牌删除失败，请重试！");
return 0;
        }
    };
    }
}
