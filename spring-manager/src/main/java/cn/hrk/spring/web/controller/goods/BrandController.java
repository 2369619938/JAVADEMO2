package cn.hrk.spring.web.controller.goods;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.feign.BrandFeign;
import cn.hrk.spring.goods.domain.Brand;
import cn.hrk.spring.oss.FileR;
import cn.hrk.spring.oss.OssUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandFeign brandService;


    @GetMapping("/findAll1")
    public List<Brand> findAl1(){
        return brandService.findAll( );
    }
    @GetMapping("/findPage1")
    public PageResult<Brand> findPage(int page, int size) {
        return brandService.findPage(page,size) ;
    }
    @PostMapping("/findList1")
    public List<Brand> findList(@RequestBody Map<String,Object>searchMap) {
        return brandService. findList(searchMap) ;
    }
    @PostMapping("/findPage1" )
    public PageResult<Brand> findPage(@RequestBody Map<String, Object> searchMap, int page, int size) {
        return brandService.findPage(searchMap,page,size);
    }
    @GetMapping("/findById1/{id}")
    public Brand findById(@PathVariable("id") Integer id) {
        return brandService.findById(id);
    }

    @PostMapping ("/add1" )
    public R add (@RequestBody Brand brand) {
        return  brandService.add( brand) ;

    }
    @PostMapping ("/update1" )
    public R update(@RequestBody Brand brand) {
        return brandService.update(brand);

    }
    @GetMapping("/delete1/{id}")
    public R delete(@PathVariable("id") Integer id) {
        return brandService.delete( id) ;

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
