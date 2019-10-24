package cn.hrk.spring.service.impl;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Spu;
import cn.hrk.spring.mapper.SpuMapper;
import cn.hrk.spring.service.ISpuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class SpuServiceImpl implements ISpuService {
    @Autowired
    private SpuMapper spuMapper;

    @Override
    public List<Spu> findAll() {
        return spuMapper.selectAll();
    }

    @Override
    public PageResult<Spu> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        List<Spu> Spupage=spuMapper.selectAll();
        PageInfo<Spu> pageInfo=new PageInfo<>(Spupage);
        return new PageResult<Spu>(pageInfo.getTotal(),Spupage);
    }

    @Override
    public List<Spu> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap) ;
        return spuMapper.selectByExample( example) ;

    }

    @Override
    public PageResult<Spu> findPage(Map<String, Object> searchMap, int page, int size) {
        Example example = createExample(searchMap) ;
        PageHelper.startPage(page,size);
        List<Spu> Spupage=spuMapper.selectByExample(example);
        PageInfo<Spu> pageInfo=new PageInfo<>(Spupage);
        return new PageResult<Spu>(pageInfo.getTotal(),Spupage);

    }

    @Override
    public Spu findById(Integer id) {
        return spuMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Spu spu) {
        spuMapper.insertUseGeneratedKeys(spu);
    }

    @Override
    public void update(Spu spu) {
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    @Override
    public void delete(Integer id) {
        spuMapper.deleteByPrimaryKey(id);
    }

    /*
     *构建查询条件
     *@param searchMap
     *@return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(Spu.class);
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
