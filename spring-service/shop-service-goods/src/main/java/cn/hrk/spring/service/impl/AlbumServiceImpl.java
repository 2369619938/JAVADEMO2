package cn.hrk.spring.service.impl;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Album;
import cn.hrk.spring.mapper.AlbumMapper;
import cn.hrk.spring.goods.service.IAlbumService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/album")
public class AlbumServiceImpl implements IAlbumService {
    @Autowired
    private AlbumMapper albumMapper;

    @GetMapping("/findAll")
    public List<Album> findAll() {
        return albumMapper.selectAll();
    }
    /**
     * 分⻚查询
     * @param page ⻚码
     * @param size 每⻚记录数
     * @return 分⻚结果
     */
    @GetMapping("/findPage")
    public PageResult<Album> findPage(@RequestParam("page") int page,
                                      @RequestParam("size") int size) {
        PageHelper.startPage(page,size);
        Page<Album> Albums = (Page<Album>) albumMapper.selectAll();
        return new PageResult<Album>
                (Albums.getTotal(),Albums.getResult());
    }
    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @PostMapping("/findList")
    public List<Album> findList(@RequestBody Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return albumMapper.selectByExample(example);
    }
    /**
     * 分⻚+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/findPage")
    public PageResult<Album> findPage(@RequestBody Map<String, Object> searchMap, @RequestParam("page") int page, @RequestParam("size") int size)
    { PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Album> Albums = (Page<Album>)
                albumMapper.selectByExample(example);
        return new PageResult<Album>
                (Albums.getTotal(),Albums.getResult()); }
    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public Album findById(@PathVariable("id") Integer id) {
        return albumMapper.selectByPrimaryKey(id);
    }




    /**
     * 新增
     * @param album
     */
    @PostMapping("/add")
    public void add(@RequestBody Album album) {
        albumMapper.insert(album);
    }

    /**
     * 修改
     * @param album
     */
    @PostMapping("/update")
    public void update(@RequestBody Album album) {
        albumMapper.updateByPrimaryKeySelective(album);
    }

    /**
     * 删除
     * @param id
     */
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id)  {
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
