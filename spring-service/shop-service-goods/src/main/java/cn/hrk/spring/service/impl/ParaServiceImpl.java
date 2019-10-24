package cn.hrk.spring.service.impl;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Brand;
import cn.hrk.spring.goods.domain.Category;
import cn.hrk.spring.goods.domain.Para;
import cn.hrk.spring.mapper.BrandMapper;
import cn.hrk.spring.mapper.ParaMapper;
import cn.hrk.spring.service.IParaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class ParaServiceImpl implements IParaService {
    @Autowired
    private ParaMapper paraMapper;

    @Override
    public List<Para> findAll() {
        return paraMapper.selectAll();
    }

    @Override
    public PageResult<Para> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        List<Para> paras=paraMapper.selectAll();
        PageInfo<Para> pageInfo=new PageInfo<>(paras);
        return new PageResult<Para>(pageInfo.getTotal(),paras);
    }

    @Override
    public List<Para> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap) ;
        return paraMapper.selectByExample( example) ;

    }

    @Override
    public PageResult<Para> findPage(Map<String, Object> searchMap, int page, int size) {
        Example example = createExample(searchMap) ;
        PageHelper.startPage(page,size);
        List<Para> paras=paraMapper.selectByExample(example);
        PageInfo<Para> pageInfo=new PageInfo<>(paras);
        return new PageResult<Para>(pageInfo.getTotal(),paras);

    }

    @Override
    public Para findById(Integer id) {
        return paraMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Para para) {
        paraMapper.insertUseGeneratedKeys(para);
    }

    @Override
    public void update(Para para) {
        paraMapper.updateByPrimaryKeySelective(para);
    }

    @Override
    public void delete(Integer id) {
        paraMapper.deleteByPrimaryKey(id);
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
