package cn.hrk.spring.web.controller.goods;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.feign.SpuFeign;
import cn.hrk.spring.goods.domain.Goods;
import cn.hrk.spring.goods.domain.Spu;
import cn.hrk.spring.oss.FileR;
import cn.hrk.spring.oss.OssUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spu")
public class SpuController {
    @Autowired
    private SpuFeign spuService;

    @GetMapping("/findAll1")
    public List<Spu> findAl1(){
        return spuService.findAll( );
    }
    @GetMapping("/findPage1")
    public PageResult<Spu> findPage(int page, int size) {
        return spuService. findPage(page,size) ;
    }
    @PostMapping("/findList1")
    public List<Spu> findList(@RequestBody Map<String,Object> searchMap) {
        return spuService. findList(searchMap) ;
    }
    @PostMapping("/findPage1" )
    public PageResult<Spu> findPage(@RequestBody Map<String,Object> searchMap, int page, int size) {
        return spuService.findPage(searchMap,page,size);
    }
    @GetMapping("/findById1/{id}")
    public Spu findById(@PathVariable("id") Integer id) {
        return spuService.findById(id);
    }
    @PostMapping ("/add1" )
    public R add (@RequestBody Spu Spu) {
        spuService.add(Spu) ;
        return R.ok();
    }
    @PostMapping ("/save1" )
    public R save (@RequestBody Goods goods) {
        spuService.saveGoods(goods);
        return R.ok();
    }

    @PostMapping ("/update1" )
    public R update(@RequestBody Spu Spu) {
        spuService.update(Spu);
        return R.ok() ;
    }
    @GetMapping("/delete1/{id}")
    public R delete(@PathVariable("id") Integer id) {
        spuService.delete( id) ;
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
