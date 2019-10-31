package cn.hrk.spring.service.impl;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Template;
import cn.hrk.spring.goods.domain.Category;
import cn.hrk.spring.goods.domain.Template;
import cn.hrk.spring.mapper.TemplateMapper;
import cn.hrk.spring.mapper.TemplateMapper;
import cn.hrk.spring.service.ITemplateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class TemplateServiceImpl implements ITemplateService {
    @Autowired
    private TemplateMapper templateMapper;

    @Override
    public List<Template> findAll() {
        return templateMapper.selectAll();
    }

    @Override
    public PageResult<Template> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        List<Template> Templatepage=templateMapper.selectAll();
        PageInfo<Template> pageInfo=new PageInfo<>(Templatepage);
        return new PageResult<Template>(pageInfo.getTotal(),Templatepage);
    }

    @Override
    public List<Template> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap) ;
        return templateMapper.selectByExample( example) ;

    }

    @Override
    public PageResult<Template> findPage(Map<String, Object> searchMap, int page, int size) {
        Example example = createExample(searchMap) ;
        PageHelper.startPage(page,size);
        List<Template> Templatepage=templateMapper.selectByExample(example);
        PageInfo<Template> pageInfo=new PageInfo<>(Templatepage);
        return new PageResult<Template>(pageInfo.getTotal(),Templatepage);

    }

    @Override
    public Template findById(Integer id) {
        return templateMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Template template) {
        templateMapper.insertUseGeneratedKeys(template);
    }

    @Override
    public void update(Template template) {
        templateMapper.updateByPrimaryKeySelective(template);
    }

    @Override
    public void delete(Integer id) {
        templateMapper.deleteByPrimaryKey(id);
    }
    
    /*
     *构建查询条件
     *@param searchMap
     *@return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(Template.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            //品牌名称
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
        }
        return example;
    }
}
