package cn.hrk.spring.service;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Spu;
import cn.hrk.spring.goods.domain.Template;

import java.util.List;
import java.util.Map;

public interface ITemplateService {
    public List<Template> findAll() ;
    public PageResult<Template> findPage(int page, int size);
    public List<Template> findList( Map<String,Object> searchMap) ;
    public PageResult<Template> findPage(Map<String, Object> searchMap, int page,int size) ;
    public Template findById(Integer id) ;
    public void add ( Template template) ;
    public void update(Template template);
    public void delete(Integer id);
}
