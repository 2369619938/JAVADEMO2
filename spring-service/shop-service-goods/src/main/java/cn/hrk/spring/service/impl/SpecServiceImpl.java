package cn.hrk.spring.service.impl;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Spec;
import cn.hrk.spring.goods.domain.Category;
import cn.hrk.spring.goods.domain.Spec;
import cn.hrk.spring.goods.domain.Template;
import cn.hrk.spring.mapper.SpecMapper;
import cn.hrk.spring.mapper.SpecMapper;
import cn.hrk.spring.mapper.TemplateMapper;
import cn.hrk.spring.service.ISpecService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class SpecServiceImpl implements ISpecService {
    @Autowired
    private SpecMapper specMapper;
    @Autowired
    private TemplateMapper templateMapper;
    @Override
    public List<Spec> findAll() {
        return specMapper.selectAll();
    }

    @Override
    public PageResult<Spec> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        List<Spec> Specpage=specMapper.selectAll();
        PageInfo<Spec> pageInfo=new PageInfo<>(Specpage);
        return new PageResult<Spec>(pageInfo.getTotal(),Specpage);
    }

    @Override
    public List<Spec> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap) ;
        return specMapper.selectByExample( example) ;

    }

    @Override
    public PageResult<Spec> findPage(Map<String, Object> searchMap, int page, int size) {
        Example example = createExample(searchMap) ;
        PageHelper.startPage(page,size);
        List<Spec> Specpage=specMapper.selectByExample(example);
        PageInfo<Spec> pageInfo=new PageInfo<>(Specpage);
        return new PageResult<Spec>(pageInfo.getTotal(),Specpage);

    }

    @Override
    public Spec findById(Integer id) {
        return specMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Spec spec) {
        specMapper.insertUseGeneratedKeys(spec);
        Template template=templateMapper.selectByPrimaryKey(spec.getTemplateId());
        template.setSpecNum(template.getSpecNum()+1);
        templateMapper.updateByPrimaryKey(template);
    }

    @Override
    public void update(Spec spec) {
        specMapper.updateByPrimaryKeySelective(spec);
    }

    @Override
    public void delete(Integer id) {
        Spec spec=specMapper.selectByPrimaryKey(id);
        specMapper.deleteByPrimaryKey(id);
        Template template=templateMapper.selectByPrimaryKey(spec.getTemplateId());
        template.setSpecNum(template.getSpecNum()-1);
        templateMapper.updateByPrimaryKey(template);
    }

    /*
     *构建查询条件
     *@param searchMap
     *@return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(Spec.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            //规格父id
            if(searchMap.get("templateId")!=null && !"".equals(searchMap.get("templateId"))){
                criteria. andEqualTo("templateId", searchMap.get("templateId"));
            }
            //规格名称
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            //规格选项
            if(searchMap. get("options") !=null && !"".equals(searchMap. get("options"))){
                criteria. andLike("options","%"+searchMap.get("options")+"%");
            }
        }
        return example;
    }
}
