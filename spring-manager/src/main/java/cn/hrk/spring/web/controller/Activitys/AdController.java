package cn.hrk.spring.web.controller.Activitys;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;

import cn.hrk.spring.activitys.domain.Ad;
import cn.hrk.spring.activitys.feign.AdFeign;
import cn.hrk.spring.oss.FileR;
import cn.hrk.spring.oss.OssUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ad")
public class AdController {
    @Autowired
    private AdFeign AdService;

    @GetMapping("/findAll1")
    public List<Ad> findAl1(){
        return AdService.findAll( );
    }
    @GetMapping("/findPage1")
    public PageResult<Ad> findPage(int page, int size) {
        return AdService. findPage(page,size) ;
    }
    @PostMapping("/findList1")
    public List<Ad> findList(@RequestBody Map<String,Object> searchMap) {
        return AdService. findList(searchMap) ;
    }
    @PostMapping("/findPage1" )
    public PageResult<Ad> findPage(@RequestBody Map<String,Object> searchMap, int page, int size) {
        return AdService.findPage(searchMap,page,size);
    }
    @GetMapping("/findById1/{id}")
    public Ad findById(@PathVariable("id") Long id) {
        return AdService.findById(id);
    }
    @PostMapping ("/add1" )
    public R add (@RequestBody Ad Ad) {
        AdService.add(Ad) ;
        return R.ok();
    }
    @PostMapping ("/update1" )
    public R update(@RequestBody Ad Ad) {
        AdService.update(Ad);
        return R.ok() ;
    }
    @GetMapping("/delete1/{id}")
    public R delete(@PathVariable("id") Long id) {
        AdService.delete( id) ;
        return R.ok();
    }
    @PostMapping("upload1")
    public R upload(@RequestParam("file") MultipartFile file){
        FileR fileR= OssUtils.upload(file);
        return R.data(fileR);
    }
    @PostMapping("uploads1")
    public R uploads(@RequestParam("file")List<MultipartFile> file){
        FileR fileR= OssUtils.upload(file);
        return R.data(fileR);
    }
}
