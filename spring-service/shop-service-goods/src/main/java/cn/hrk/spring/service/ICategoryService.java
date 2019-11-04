package cn.hrk.spring.service;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Category;

import java.util.List;
import java.util.Map;

public interface ICategoryService {
    public List<Category> findAll() ;
    public PageResult<Category> findPage(int page, int size);
    public List<Category> findList( Map<String,Object> searchMap) ;
    public PageResult<Category> findPage(Map<String, Object> searchMap, int page,int size) ;
    public Category findById(Integer id) ;
    public void add ( Category category) ;
    public void update(Category category);
    public int delete(Integer id);
}
