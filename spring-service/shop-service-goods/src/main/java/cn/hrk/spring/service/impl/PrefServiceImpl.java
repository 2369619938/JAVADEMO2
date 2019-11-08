package cn.hrk.spring.service.impl;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Pref;
import cn.hrk.spring.goods.domain.Brand;
import cn.hrk.spring.mapper.PrefMapper;
import cn.hrk.spring.goods.service.IPrefService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pref")
public class PrefServiceImpl implements IPrefService {

    @Autowired
    private PrefMapper prefMapper;
    @GetMapping("/findAll")
    public List<Pref> findAll() {
        return prefMapper.selectAll();
    }
    /**
     * 分⻚查询
     * @param page ⻚码
     * @param size 每⻚记录数
     * @return 分⻚结果
     */
    @GetMapping("/findPage")
    public PageResult<Pref> findPage(@RequestParam("page") int page,
                                      @RequestParam("size") int size) {
        PageHelper.startPage(page,size);
        Page<Pref> prefs = (Page<Pref>) prefMapper.selectAll();
        return new PageResult<Pref>
                (prefs.getTotal(),prefs.getResult());
    }
    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @PostMapping("/findList")
    public List<Pref> findList(@RequestBody Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return prefMapper.selectByExample(example);
    }
    /**
     * 分⻚+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/findPage")
    public PageResult<Pref> findPage(@RequestBody Map<String, Object> searchMap, @RequestParam("page") int page, @RequestParam("size") int size)
    { PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Pref> Prefs = (Page<Pref>)
                prefMapper.selectByExample(example);
        return new PageResult<Pref>
                (Prefs.getTotal(),Prefs.getResult()); }
    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public Pref findById(@PathVariable("id") Integer id) {
        return prefMapper.selectByPrimaryKey(id);
    }




    /**
     * 新增
     * @param pref
     */
    @PostMapping("/add")
    public void add(@RequestBody Pref pref) {
        prefMapper.insert(pref);
    }

    /**
     * 修改
     * @param pref
     */
    @PostMapping("/update")
    public void update(@RequestBody Pref pref) {
        prefMapper.updateByPrimaryKeySelective(pref);
    }

    /**
     * 删除
     * @param id
     */
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id)  {
        prefMapper.deleteByPrimaryKey(id);
    }
    /*
     *构建查询条件
     *@param searchMap
     *@return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
//        if(searchMap!=null){
//            //品牌名称
//            if(searchMap.get("name")!=null && !"".equals(searchMap.get("nane"))){
//                criteria.andLike("name","%"+searchMap.get("nane")+"%");
//            }
//            //品牌图片地址
//            if(searchMap. get("image") !=null && !"".equals(searchMap. get("image"))){
//                criteria. andLike("image","%"+searchMap.get("image")+"%");
//            }
//            //品牌的首字母
//            if(searchMap. get("letter")!=null && !"".equals(searchMap.get("letter"))){
//                criteria. andLike("letter","%"+searchMap.get("letter")+"%");
//            }
//            //品牌id
//            if(searchMap.get("id")!=null ){
//                criteria. andEqualTo("id", searchMap.get("id"));
//            }
//            //排序
//            if(searchMap.get("seq") !=null ){
//                criteria. andEqualTo("seq",searchMap.get("seq"));
//            }
//        }
        return example;
    }
}
