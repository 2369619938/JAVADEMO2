package cn.hrk.spring.web.controller.goods;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.goods.feign.SkuFeign;
import cn.hrk.spring.goods.domain.Sku;
import cn.hrk.spring.oss.FileR;
import cn.hrk.spring.oss.OssUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sku")
public class SkuController {
    @Autowired
    private SkuFeign skuService;

    @GetMapping("/findAll1")
    public List<Sku> findAl1(){
        return skuService.findAll( );
    }
    @GetMapping("/findPage1")
    public PageResult<Sku> findPage(int page, int size) {
        return skuService. findPage(page,size) ;
    }
    @PostMapping("/findList1")
    public List<Sku> findList(@RequestBody Map<String,Object> searchMap) {
        return skuService. findList(searchMap) ;
    }
    @PostMapping("/findPage1" )
    public PageResult<Sku> findPage(@RequestBody Map<String,Object> searchMap, int page, int size) {
        return skuService.findPage(searchMap,page,size);
    }
    @GetMapping("/findById1/{id}")
    public Sku findById(@PathVariable("id") Integer id) {
        return skuService.findById(id);
    }
    @PostMapping ("/add1" )
    public R add (@RequestBody Sku Sku) {
        skuService.add(Sku) ;
        return R.ok();
    }
    @PostMapping ("/update1" )
    public R update(@RequestBody Sku Sku) {
        skuService.update(Sku);
        return R.ok() ;
    }
    @GetMapping("/delete1/{id}")
    public R delete(@PathVariable("id") Integer id) {
        skuService.delete( id) ;
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
