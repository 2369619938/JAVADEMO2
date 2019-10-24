package cn.hrk.spring.service.impl;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Brand;
import cn.hrk.spring.goods.domain.Category;
import cn.hrk.spring.goods.domain.Pref;
import cn.hrk.spring.mapper.BrandMapper;
import cn.hrk.spring.mapper.PrefMapper;
import cn.hrk.spring.service.IPrefService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class PrefServiceImpl implements IPrefService {

    @Autowired
    private PrefMapper prefMapper;
    @Override
    public List<Pref> findAll() {
        return prefMapper.selectAll();
    }

    @Override
    public PageResult<Pref> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        List<Pref> prefs=prefMapper.selectAll();
        PageInfo<Pref> pageInfo=new PageInfo<>(prefs);
        return new PageResult<Pref>(pageInfo.getTotal(),prefs);
    }

    @Override
    public List<Pref> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap) ;
        return prefMapper.selectByExample( example) ;

    }

    @Override
    public PageResult<Pref> findPage(Map<String, Object> searchMap, int page, int size) {
        Example example = createExample(searchMap) ;
        PageHelper.startPage(page,size);
        List<Pref> prefs=prefMapper.selectByExample(example);
        PageInfo<Pref> pageInfo=new PageInfo<>(prefs);
        return new PageResult<Pref>(pageInfo.getTotal(),prefs);

    }

    @Override
    public Pref findById(Integer id) {
        return prefMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Pref pref) {
        prefMapper.insertUseGeneratedKeys(pref);
    }

    @Override
    public void update(Pref pref) {
        prefMapper.updateByPrimaryKeySelective(pref);
    }

    @Override
    public void delete(Integer id) {
        prefMapper.deleteByPrimaryKey(id);
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
