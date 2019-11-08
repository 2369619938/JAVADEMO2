package cn.hrk.spring.service.impl;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Category;
import cn.hrk.spring.mapper.CategoryMapper;
import cn.hrk.spring.goods.service.ICategoryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping("/findAll")
    public List<Category> findAll() {
        return categoryMapper.selectAll();
    }
    /**
     * 分⻚查询
     * @param page ⻚码
     * @param size 每⻚记录数
     * @return 分⻚结果
     */
    @GetMapping("/findPage")
    public PageResult<Category> findPage(@RequestParam("page") int page,
                                      @RequestParam("size") int size) {
        PageHelper.startPage(page,size);
        Page<Category> Categorys = (Page<Category>) categoryMapper.selectAll();
        return new PageResult<Category>
                (Categorys.getTotal(),Categorys.getResult());
    }
    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @PostMapping("/findList")
    public List<Category> findList(@RequestBody Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return categoryMapper.selectByExample(example);
    }
    /**
     * 分⻚+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/findPage")
    public PageResult<Category> findPage(@RequestBody Map<String, Object> searchMap, @RequestParam("page") int page, @RequestParam("size") int size)
    { PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Category> categories = (Page<Category>)
                categoryMapper.selectByExample(example);
        return new PageResult<Category>
                (categories.getTotal(),categories.getResult()); }
    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public Category findById(@PathVariable("id") Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }




    /**
     * 新增
     * @param category
     */
    @PostMapping("/add")
    public void add(@RequestBody Category category) {
        categoryMapper.insert(category);
    }

    /**
     * 修改
     * @param category
     */
    @PostMapping("/update")
    public void update(@RequestBody Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    /**
     * 删除
     * @param id
     */
    @GetMapping("/delete/{id}")
    public int delete(@PathVariable("id") Integer id)  {
      return   categoryMapper.deleteByPrimaryKey(id);
    }

    /*
     *构建查询条件
     *@param searchMap
     *@return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            //品牌名称
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            //父id
            if(searchMap.get("parentId")!=null ){
                criteria. andEqualTo("parentId", searchMap.get("parentId"));
            }
            //id
            if(searchMap.get("Id")!=null ){
                criteria. andEqualTo("Id", searchMap.get("Id"));
            }
            //排序
            if(searchMap.get("seq") !=null ){
                criteria. andEqualTo("seq",searchMap.get("seq"));
            }
        }
        return example;
    }

}
