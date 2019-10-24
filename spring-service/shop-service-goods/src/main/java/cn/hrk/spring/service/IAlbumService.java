package cn.hrk.spring.service;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Album;
import cn.hrk.spring.goods.domain.Brand;

import java.util.List;
import java.util.Map;

public interface IAlbumService {
    public List<Album> findAll() ;
    public PageResult<Album> findPage(int page, int size);
    public List<Album> findList( Map<String,Object> searchMap) ;
    public PageResult<Album> findPage(Map<String, Object> searchMap, int page,int size) ;
    public Album findById(Integer id) ;
    public void add ( Album album) ;
    public void update(Album album);
    public void delete(Integer id);
}
