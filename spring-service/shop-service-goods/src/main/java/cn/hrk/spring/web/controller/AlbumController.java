package cn.hrk.spring.web.controller;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.goods.domain.Album;
import cn.hrk.spring.goods.domain.Brand;
import cn.hrk.spring.service.IAlbumService;
import cn.hrk.spring.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private IAlbumService albumService;

    @GetMapping("/findAll")
    public List<Album> findAl1(){
        return albumService.findAll( );
    }
    @GetMapping("/findPage")
    public PageResult<Album> findPage(int page, int size) {
        return albumService. findPage(page,size) ;
    }
    @PostMapping("/findList")
    public List<Album> findList(@RequestBody Map<String,Object> searchMap) {
        return albumService. findList(searchMap) ;
    }
    @PostMapping("/findPage" )
    public PageResult<Album> findPage(@RequestBody Map<String,Object> searchMap, int page, int size) {
        return albumService.findPage(searchMap,page,size);
    }
    @GetMapping("/findById/{id}")
    public Album findById(@PathVariable("id") Integer id) {
        return albumService.findById(id);
    }
    @PostMapping ("/add" )
    public R add (@RequestBody Album album) {
        albumService.add(album) ;
        return R.ok();
    }
    @PostMapping ("/update" )
    public R update(@RequestBody Album album) {
        albumService.update(album);
        return R.ok() ;
    }
    @GetMapping("/delete/{id}")
    public R delete(@PathVariable("id") Integer id) {
        albumService.delete( id) ;
        return R.ok();
    }
}
