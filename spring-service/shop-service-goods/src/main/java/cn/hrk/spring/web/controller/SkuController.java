package cn.hrk.spring.web.controller;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.goods.domain.Sku;
import cn.hrk.spring.oss.FileR;
import cn.hrk.spring.oss.OssUtils;
import cn.hrk.spring.service.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sku")
public class SkuController {
    @Autowired
    private ISkuService skuService;

    @GetMapping("/findAll")
    public List<Sku> findAl1(){
        return skuService.findAll( );
    }
    @GetMapping("/findPage")
    public PageResult<Sku> findPage(int page, int size) {
        return skuService. findPage(page,size) ;
    }
    @PostMapping("/findList")
    public List<Sku> findList(@RequestBody Map<String,Object> searchMap) {
        return skuService. findList(searchMap) ;
    }
    @PostMapping("/findPage" )
    public PageResult<Sku> findPage(@RequestBody Map<String,Object> searchMap, int page, int size) {
        return skuService.findPage(searchMap,page,size);
    }
    @GetMapping("/findById/{id}")
    public Sku findById(@PathVariable("id") Integer id) {
        return skuService.findById(id);
    }
    @PostMapping ("/add" )
    public R add (@RequestBody Sku Sku) {
        skuService.add(Sku) ;
        return R.ok();
    }
    @PostMapping ("/update" )
    public R update(@RequestBody Sku Sku) {
        skuService.update(Sku);
        return R.ok() ;
    }
    @GetMapping("/delete/{id}")
    public R delete(@PathVariable("id") Integer id) {
        skuService.delete( id) ;
        return R.ok();
    }
    @PostMapping("upload")
    public R upload(@RequestParam("file") MultipartFile file){
        FileR fileR= OssUtils.upload(file);
        return R.data(fileR);
    }
    @PostMapping("uploads")
    public R uploads(@RequestParam("file")List<MultipartFile> file){
        FileR fileR= OssUtils.upload(file);
        return R.data(fileR);
    }
}
