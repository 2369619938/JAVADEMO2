package cn.hrk.spring.service.impl;

import cn.hrk.common.domain.IdWorker;
import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.*;
import cn.hrk.spring.mapper.CategoryBrandMapper;
import cn.hrk.spring.mapper.CategoryMapper;
import cn.hrk.spring.mapper.SkuMapper;
import cn.hrk.spring.mapper.SpuMapper;
import cn.hrk.spring.service.ISpuService;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SpuServiceImpl implements ISpuService {

    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryBrandMapper categoryBrandMapper;
    IdWorker idWorker=new IdWorker();
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



    @Transactional
    public void saveGoods(Goods goods) {
        //保存⼀个spu的信息
        Spu spu=goods.getSpu();
        if (spu.getId()==null){
            spu.setId(idWorker.nextId()+"");
            spuMapper.insert(spu);
        }else {
            //删除原来的sku列表
            Example example=new Example(Sku.class);
            Example.Criteria criteria=example.createCriteria();
            criteria.andEqualTo("spuId",spu.getId());
            skuMapper.deleteByExample(example);
            //执⾏spu的修改
            spuMapper.updateByPrimaryKeySelective(spu);
        }

        //保存sku列表的信息
        Date date=new Date();
        //分类对象
        Category category= categoryMapper.selectByPrimaryKey(spu.getCategory3Id());
        List<Sku> skuList=goods.getSkuList();
        for(Sku sku:skuList){
            if (sku.getId()==null){
                sku.setId(idWorker.nextId()+"");


                sku.setCreateTime(date);//创建⽇期
            }
            sku.setSpuId(spu.getId()+"");
            //不启⽤规格的sku处理
            if(sku.getSpec()==null||"".equals(sku.getSpec())){
                sku.setSpec("{}");
            }
            //sku名称=spu名称+规格值列表
            String name=spu.getName();
            //sku.getSpec(){"颜⾊":"红","机身内存":"64G"}
            Map<String,String>specMap= JSON.parseObject(sku.getSpec(),Map.class);

            for(String value:specMap.values()){
                name+=""+value;
            }
            sku.setName(name);//名称
            sku.setUpdateTime(date);//修改⽇期
            sku.setCategoryId(spu.getCategory3Id());//分类id
            sku.setCategoryName(category.getName());//分类名称sku.setCommentNum(0);//评论数
            sku.setSaleNum(0);//销售数量

            skuMapper.insert(sku);

        }
        //建⽴分类和品牌的关联
        CategoryBrand categoryBrand=new CategoryBrand();
        categoryBrand.setCategoryId(spu.getCategory3Id());
        categoryBrand.setBrandId(spu.getBrandId());
        int count=categoryBrandMapper.selectCount(categoryBrand);
        if(count==0){
            categoryBrandMapper.insert(categoryBrand);
        }
    }

    /*
     *构建查询条件
     *@param searchMap
     *@return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            //品牌名称
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("nane"))){
                criteria.andLike("name","%"+searchMap.get("nane")+"%");
            }
            if(searchMap.get("templateId")!=null && !"".equals(searchMap.get("templateId"))){
                criteria. andEqualTo("templateId", searchMap.get("templateId"));
            }
            if(searchMap.get("static")!=null && !"".equals(searchMap.get("static"))){
                criteria. andEqualTo("static", searchMap.get("static"));
            }
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
           }
        return example;
    }
}
