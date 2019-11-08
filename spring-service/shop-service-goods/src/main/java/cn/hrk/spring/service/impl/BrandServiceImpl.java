package cn.hrk.spring.service.impl;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.goods.domain.Brand;
import cn.hrk.spring.goods.service.IBrandService;
import cn.hrk.spring.mapper.BrandMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandServiceImpl implements IBrandService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BrandServiceImpl.class);
    @Autowired
    private BrandMapper brandMapper;


    @GetMapping("/findAll")
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }
    /**
     * 分⻚查询
     * @param page ⻚码
     * @param size 每⻚记录数
     * @return 分⻚结果
     */
    @GetMapping("/findPage")
    public PageResult<Brand> findPage(@RequestParam("page") int page,
                                      @RequestParam("size") int size) {
        PageHelper.startPage(page,size);
        Page<Brand> brands = (Page<Brand>) brandMapper.selectAll();
        return new PageResult<Brand>
                (brands.getTotal(),brands.getResult());
    }
    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @PostMapping("/findList")
    public List<Brand> findList(@RequestBody Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return brandMapper.selectByExample(example);
    }
    /**
     * 分⻚+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/findPage")
    public PageResult<Brand> findPage(@RequestBody Map<String, Object> searchMap,@RequestParam("page") int page,@RequestParam("size") int size)
    { PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Brand> brands = (Page<Brand>)
                brandMapper.selectByExample(example);
        return new PageResult<Brand>
                (brands.getTotal(),brands.getResult()); }
    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public Brand findById(@PathVariable("id") Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param brand
     */
    @PostMapping("/add")
    public R add(@RequestBody Brand brand) {
        if( brandMapper.insert(brand)>0){
            return R.ok();
        }
        return R.error(400,"失败");

    }
    /**
     * 修改
     * @param brand
     */
    @PostMapping("/update")
    public R update(@RequestBody Brand brand) {
        if(brandMapper.updateByPrimaryKeySelective(brand)>0){
            return R.ok();
        }
        return R.error(400,"失败");
    }
    /**
     * 删除
     * @param id
     */
    @GetMapping("/delete/{id}")
    public R delete(@PathVariable("id") Integer id) {
        if(brandMapper.deleteByPrimaryKey(id)>0){
           return R.ok();
        }
        return R.error(400,"失败");
    }
    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){ // 品牌名称
            if(searchMap.get("name")!=null &&
                    !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            // 品牌图⽚地址
            if(searchMap.get("image")!=null &&
                    !"".equals(searchMap.get("image"))){
                criteria.andLike("image","%"+searchMap.get("image")+"%");
            }
            // 品牌的⾸字⺟
            if(searchMap.get("letter")!=null &&
                    !"".equals(searchMap.get("letter"))){

                criteria.andLike("letter","%"+searchMap.get("letter")+"%");
            }
            // 品牌id
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }
            // 排序
            if(searchMap.get("seq")!=null ){
                criteria.andEqualTo("seq",searchMap.get("seq"));
            }
        }
        return example;
    }

}
