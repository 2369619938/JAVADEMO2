package cn.hrk.spring.service.impl;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Album;
import cn.hrk.spring.goods.domain.Brand;
import cn.hrk.spring.mapper.AlbumMapper;
import cn.hrk.spring.mapper.BrandMapper;
import cn.hrk.spring.service.IAlbumService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class AlbumServiceImpl implements IAlbumService {
    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public List<Album> findAll() {
        return albumMapper.selectAll();
    }

    @Override
    public PageResult<Album> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        List<Album> albums=albumMapper.selectAll();
        PageInfo<Album> pageInfo=new PageInfo<>(albums);
        return new PageResult<Album>(pageInfo.getTotal(),albums);
    }

    @Override
    public List<Album> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap) ;
        return albumMapper.selectByExample( example) ;
    }

    @Override
    public PageResult<Album> findPage(Map<String, Object> searchMap, int page, int size) {
        Example example = createExample(searchMap) ;
        PageHelper.startPage(page,size);
        List<Album> albums=albumMapper.selectByExample(example);
        PageInfo<Album> pageInfo=new PageInfo<>(albums);
        return new PageResult<Album>(pageInfo.getTotal(),albums);
    }

    @Override
    public Album findById(Integer id) {
            return albumMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Album album) {
        albumMapper.insert(album);
    }

    @Override
    public void update(Album album) {
        albumMapper.updateByPrimaryKeySelective(album);
    }

    @Override
    public void delete(Integer id)  {
        albumMapper.deleteByPrimaryKey(id);
    }
    /*
     *构建查询条件
     *@param searchMap
     *@return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(Album.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            //相册名称
            if(searchMap.get("title")!=null && !"".equals(searchMap.get("title"))){
                criteria.andLike("title","%"+searchMap.get("title")+"%");
            }
            //相册图片地址
            if(searchMap. get("image") !=null && !"".equals(searchMap. get("image"))){
                criteria. andLike("image","%"+searchMap.get("image")+"%");
            }
            //相册列表
            if(searchMap.get("imageItems")!=null && !"".equals(searchMap.get("imageItems"))){
                criteria.andLike("imageItems","%"+searchMap.get("imageItems")+"%");
            }
            //品牌id
            if(searchMap.get("id")!=null ){
                criteria. andEqualTo("id", searchMap.get("id"));
            }
        }
        return example;
    }
}
