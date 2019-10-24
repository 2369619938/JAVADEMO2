package cn.hrk.spring.service;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Pref;
import cn.hrk.spring.goods.domain.Sku;

import java.util.List;
import java.util.Map;

public interface ISkuService {
    public List<Sku> findAll() ;
    public PageResult<Sku> findPage(int page, int size);
    public List<Sku> findList( Map<String,Object> searchMap) ;
    public PageResult<Sku> findPage(Map<String, Object> searchMap, int page,int size) ;
    public Sku findById(Integer id) ;
    public void add ( Sku sku) ;
    public void update(Sku sku);
    public void delete(Integer id);
}
