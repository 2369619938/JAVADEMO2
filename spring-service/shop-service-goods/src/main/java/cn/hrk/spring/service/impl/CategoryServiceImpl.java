package cn.hrk.spring.service.impl;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Brand;
import cn.hrk.spring.goods.domain.Category;
import cn.hrk.spring.mapper.BrandMapper;
import cn.hrk.spring.mapper.CategoryMapper;
import cn.hrk.spring.service.ICategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public PageResult<Category> findPage(int page, int size) {

        PageHelper.startPage(page,size);
        List<Category> categories=categoryMapper.selectAll();
        PageInfo<Category> pageInfo=new PageInfo<>(categories);
        return new PageResult<Category>(pageInfo.getTotal(),categories);
    }

    @Override
    public List<Category> findList(Map<String, Object> searchMap) {

        Example example = createExample(searchMap) ;
        return categoryMapper.selectByExample( example) ;
    }

    @Override
    public PageResult<Category> findPage(Map<String, Object> searchMap, int page, int size) {
        Example example = createExample(searchMap) ;
        PageHelper.startPage(page,size);
        List<Category> categories=categoryMapper.selectByExample(example);
        PageInfo<Category> pageInfo=new PageInfo<>(categories);
        return new PageResult<Category>(pageInfo.getTotal(),categories);
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Category category) {
        categoryMapper.insertUseGeneratedKeys(category);
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.deleteByPrimaryKey(id);
    }


    /*
     *构建查询条件
     *@param searchMap
     *@return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
//        if(searchMap!=null){
//            //品牌名称
//            if(searchMap.get("name")!=null && !"".equals(searchMap.get("nane"))){
//                criteria.andLike("name","%"+searchMap.get("nane")+"%");
//            }
//            //品牌图片地址
//            if(searchMap. get("image") !=null && !"".equals(searchMap. get("image"))){
//                criteria. andLike("image","%"+searchMap.get("image")+"%");
//            }
//            //品牌的首字母
//            if(searchMap. get("letter")!=null && !"".equals(searchMap.get("letter"))){
//                criteria. andLike("letter","%"+searchMap.get("letter")+"%");
//            }
//            //品牌id
//            if(searchMap.get("id")!=null ){
//                criteria. andEqualTo("id", searchMap.get("id"));
//            }
//            //排序
//            if(searchMap.get("seq") !=null ){
//                criteria. andEqualTo("seq",searchMap.get("seq"));
//            }
//        }
        return example;
    }

}
