package cn.hrk.spring.service;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Para;
import cn.hrk.spring.goods.domain.Pref;

import java.util.List;
import java.util.Map;

public interface IPrefService {
    public List<Pref> findAll() ;
    public PageResult<Pref> findPage(int page, int size);
    public List<Pref> findList( Map<String,Object> searchMap) ;
    public PageResult<Pref> findPage(Map<String, Object> searchMap, int page,int size) ;
    public Pref findById(Integer id) ;
    public void add ( Pref pref) ;
    public void update(Pref pref);
    public void delete(Integer id);
}
