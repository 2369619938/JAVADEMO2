package cn.hrk.spring.service.impl;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.*;
import cn.hrk.spring.mapper.BrandMapper;
import cn.hrk.spring.mapper.ParaMapper;
import cn.hrk.spring.mapper.TemplateMapper;
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
    @Autowired
    private TemplateMapper templateMapper;
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
        Template template=templateMapper.selectByPrimaryKey(para.getTemplateId());
        template.setSpecNum(template.getSpecNum()+1);
        templateMapper.updateByPrimaryKey(template);
    }

    @Override
    public void update(Para para) {
        paraMapper.updateByPrimaryKeySelective(para);
    }

    @Override
    public void delete(Integer id) {

        Para para=paraMapper.selectByPrimaryKey(id);
        paraMapper.deleteByPrimaryKey(id);
        Template template=templateMapper.selectByPrimaryKey(para.getTemplateId());
        template.setSpecNum(template.getSpecNum()-1);
        templateMapper.updateByPrimaryKey(template);
    }

    /*
     *构建查询条件
     *@param searchMap
     *@return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(Para.class);
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
