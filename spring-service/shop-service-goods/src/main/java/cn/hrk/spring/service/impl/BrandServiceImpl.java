package cn.hrk.spring.service.impl;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Brand;
import cn.hrk.spring.mapper.BrandMapper;
import cn.hrk.spring.oss.OssUtils;
import cn.hrk.spring.service.IBrandService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements IBrandService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BrandServiceImpl.class);
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    @Override
    public PageResult<Brand> findPage(int page, int size) {
        LOGGER.debug("AAAAA");
        PageHelper.startPage(page,size);
        List<Brand> brandpage=brandMapper.selectAll();
        PageInfo<Brand> pageInfo=new PageInfo<>(brandpage);
        return new PageResult<Brand>(pageInfo.getTotal(),brandpage);
    }

    @Override
    public List<Brand> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap) ;
        return brandMapper.selectByExample( example) ;

    }

    @Override
    public PageResult<Brand> findPage(Map<String, Object> searchMap, int page, int size) {
        LOGGER.debug("AAAAA");
        Example example = createExample(searchMap);
        PageHelper.startPage(page,size);
        List<Brand> brandpage=brandMapper.selectByExample(example);
        PageInfo<Brand> pageInfo=new PageInfo<>(brandpage);
        return new PageResult<Brand>(pageInfo.getTotal(),brandpage);

    }

    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Brand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    /*
    *构建查询条件
    *@param searchMap
    *@return
    */
    private Example createExample(Map<String,Object> searchMap){
    Example example=new Example(Brand.class);
    Example.Criteria criteria = example.createCriteria();

    if(searchMap!=null){
    //品牌名称
    if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
    criteria.andLike("name","%"+searchMap.get("name")+"%");
    }
    //品牌图片地址
    if(searchMap. get("image") !=null && !"".equals(searchMap.get("image"))){
        criteria. andLike("image","%"+searchMap.get("image")+"%");
    }
    //品牌的首字母
    if(searchMap. get("letter")!=null && !"".equals(searchMap.get("letter"))){
    criteria. andLike("letter","%"+searchMap.get("letter")+"%");
    }
    //品牌id
    if(searchMap.get("id")!=null ){
        criteria. andEqualTo("id", searchMap.get("id"));
    }
    //排序
    if(searchMap.get("seq") !=null ){
        criteria. andEqualTo("seq",searchMap.get("seq"));
    }
    }
    return example;
    }


}
