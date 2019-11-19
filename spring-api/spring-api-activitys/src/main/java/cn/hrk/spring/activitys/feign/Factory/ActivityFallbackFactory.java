package cn.hrk.spring.activitys.feign.Factory;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.activitys.domain.Activity;
import cn.hrk.spring.activitys.feign.ActivityFeign;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ActivityFallbackFactory implements FallbackFactory<ActivityFeign>
{
    @Override
    public ActivityFeign create(Throwable throwable)
    {
    log.error(throwable.getMessage());
    return new ActivityFeign() {
        @Override
        public List<Activity> findAll() {
            log.info("查询全部品牌失败，请重试！");
            return null;
        }

        @Override
        public PageResult<Activity> findPage(int page, int size) {
            log.info("分⻚查询品牌失败，请重试！");
            return null;
        }

        @Override
        public List<Activity> findList(Map<String, Object> searchMap) {
            log.info("条件查询品牌失败，请重试！");
            return null;
        }

        @Override
        public PageResult<Activity> findPage(Map<String, Object>searchMap, int page, int size) {
            log.info("分⻚条件查询品牌失败，请重试！");
            return null;
        }

        @Override
        public Activity findById(Long id) {
            log.info("查询指定ID品牌失败，请重试！");
            return null;
        }

        @Override
        public void add(Activity Activity) {
            log.info("品牌添加失败，请重试！");

        }

        @Override
        public void update(Activity Activity) {
            log.info("品牌修改失败，请重试！");

        }

        @Override
        public void delete(Long id) {
            log.info("品牌删除失败，请重试！");

        }

    };
    }
}
