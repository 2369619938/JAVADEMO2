package cn.hrk.spring.service;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Spec;
import cn.hrk.spring.goods.domain.Spu;

import java.util.List;
import java.util.Map;

public interface ISpuService {
    public List<Spu> findAll() ;
    public PageResult<Spu> findPage(int page, int size);
    public List<Spu> findList( Map<String,Object> searchMap) ;
    public PageResult<Spu> findPage(Map<String, Object> searchMap, int page,int size) ;
    public Spu findById(Integer id) ;
    public void add ( Spu spu) ;
    public void update(Spu spu);
    public void delete(Integer id);
}
