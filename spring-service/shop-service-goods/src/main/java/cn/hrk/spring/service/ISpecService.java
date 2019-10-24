package cn.hrk.spring.service;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Sku;
import cn.hrk.spring.goods.domain.Spec;

import java.util.List;
import java.util.Map;

public interface ISpecService {
    public List<Spec> findAll() ;
    public PageResult<Spec> findPage(int page, int size);
    public List<Spec> findList( Map<String,Object> searchMap) ;
    public PageResult<Spec> findPage(Map<String, Object> searchMap, int page,int size) ;
    public Spec findById(Integer id) ;
    public void add ( Spec spec) ;
    public void update(Spec spec);
    public void delete(Integer id);
}
