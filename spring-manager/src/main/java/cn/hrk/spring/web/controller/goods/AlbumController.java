package cn.hrk.spring.web.controller.goods;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.goods.feign.AlbumFeign;
import cn.hrk.spring.goods.domain.Album;
import cn.hrk.spring.oss.FileR;
import cn.hrk.spring.oss.OssUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumFeign albumService;

    @GetMapping("/findAll1")
    public List<Album> findAl1(){
        return albumService.findAll( );
    }
    @GetMapping("/findPage1")
    public PageResult<Album> findPage(int page, int size) {
        return albumService. findPage(page,size) ;
    }
    @PostMapping("/findList1")
    public List<Album> findList(@RequestBody Map<String,Object> searchMap) {
        return albumService. findList(searchMap) ;
    }
    @PostMapping("/findPage1" )
    public PageResult<Album> findPage(@RequestBody Map<String,Object> searchMap, int page, int size) {
        return albumService.findPage(searchMap,page,size);
    }
    @GetMapping("/findById1/{id}")
    public Album findById(@PathVariable("id") Integer id) {
        return albumService.findById(id);
    }
    @PostMapping ("/add1" )
    public R add (@RequestBody Album album) {
        albumService.add(album) ;
        return R.ok();
    }
    @PostMapping ("/update1" )
    public R update(@RequestBody Album album) {
        albumService.update(album);
        return R.ok() ;
    }
    @GetMapping("/delete1/{id}")
    public R delete(@PathVariable("id") Integer id) {
        albumService.delete( id) ;
        return R.ok();
    }
    @PostMapping("upload1")
    public R upload(@RequestParam("file")MultipartFile file){
        FileR fileR= OssUtils.upload(file);
        return R.data(fileR);
    }
    @PostMapping("uploads1")
    public R uploads(@RequestParam("file")List<MultipartFile> file){
        FileR fileR= OssUtils.upload(file);
        return R.data(fileR);
    }
}
