package cn.hrk.spring.goods.feign.Factory;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Album;
import cn.hrk.spring.goods.feign.AlbumFeign;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class RemoteAlbumFallbackFactory implements FallbackFactory<AlbumFeign>
{
    @Override
    public AlbumFeign create(Throwable throwable)
    {
    log.error(throwable.getMessage());
    return new AlbumFeign() {
        @Override
        public List<Album> findAll() {
            log.info("查询全部品牌失败，请重试！");
            return null;
        }

        @Override
        public PageResult<Album> findPage(int page, int size) {
            log.info("分⻚查询品牌失败，请重试！");
            return null;
        }

        @Override
        public List<Album> findList(Map<String, Object> searchMap) {
            log.info("条件查询品牌失败，请重试！");
            return null;
        }

        @Override
        public PageResult<Album> findPage(Map<String, Object>searchMap, int page, int size) {
            log.info("分⻚条件查询品牌失败，请重试！");
            return null;
        }

        @Override
        public Album findById(Integer id) {
            log.info("查询指定ID品牌失败，请重试！");
            return null;
        }

        @Override
        public void add(Album Album) {
            log.info("品牌添加失败，请重试！");

        }

        @Override
        public void update(Album Album) {
            log.info("品牌修改失败，请重试！");

        }

        @Override
        public void delete(Integer id) {
            log.info("品牌删除失败，请重试！");

        }
    };
    }
}
