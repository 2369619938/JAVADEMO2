package cn.hrk.spring.service;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Category;
import cn.hrk.spring.goods.domain.Para;

import java.util.List;
import java.util.Map;

public interface IParaService {
    public List<Para> findAll() ;
    public PageResult<Para> findPage(int page, int size);
    public List<Para> findList( Map<String,Object> searchMap) ;
    public PageResult<Para> findPage(Map<String, Object> searchMap, int page,int size) ;
    public Para findById(Integer id) ;
    public void add ( Para para) ;
    public void update(Para para);
    public void delete(Integer id);
}
