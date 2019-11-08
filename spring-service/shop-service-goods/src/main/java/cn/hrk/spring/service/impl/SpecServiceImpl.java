package cn.hrk.spring.service.impl;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Spec;
import cn.hrk.spring.goods.domain.Template;
import cn.hrk.spring.mapper.SpecMapper;
import cn.hrk.spring.mapper.TemplateMapper;
import cn.hrk.spring.goods.service.ISpecService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spec")
public class SpecServiceImpl implements ISpecService {
    @Autowired
    private SpecMapper specMapper;
    @Autowired
    private TemplateMapper templateMapper;
    @GetMapping("/findAll")
    public List<Spec> findAll() {
        return specMapper.selectAll();
    }
    /**
     * 分⻚查询
     * @param page ⻚码
     * @param size 每⻚记录数
     * @return 分⻚结果
     */
    @GetMapping("/findPage")
    public PageResult<Spec> findPage(@RequestParam("page") int page,
                                     @RequestParam("size") int size) {
        PageHelper.startPage(page,size);
        Page<Spec> specs = (Page<Spec>) specMapper.selectAll();
        return new PageResult<Spec>
                (specs.getTotal(),specs.getResult());
    }
    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @PostMapping("/findList")
    public List<Spec> findList(@RequestBody Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return specMapper.selectByExample(example);
    }
    /**
     * 分⻚+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/findPage")
    public PageResult<Spec> findPage(@RequestBody Map<String, Object> searchMap, @RequestParam("page") int page, @RequestParam("size") int size)
    { PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Spec> specs = (Page<Spec>)
                specMapper.selectByExample(example);
        return new PageResult<Spec>
                (specs.getTotal(),specs.getResult()); }
    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public Spec findById(@PathVariable("id") Integer id) {
        return specMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param spec
     */
    @PostMapping("/add")
    public void add(@RequestBody Spec spec) {
        specMapper.insertUseGeneratedKeys(spec);
        Template template=templateMapper.selectByPrimaryKey(spec.getTemplateId());
        template.setSpecNum(template.getSpecNum()+1);
        templateMapper.updateByPrimaryKey(template);
    }

    /**
     * 修改
     * @param spec
     */
    @PostMapping("/update")
    public void update(@RequestBody Spec spec) {
        specMapper.updateByPrimaryKeySelective(spec);
    }

    /**
     * 删除
     * @param id
     */
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        Spec spec=specMapper.selectByPrimaryKey(id);
        specMapper.deleteByPrimaryKey(id);
        Template template=templateMapper.selectByPrimaryKey(spec.getTemplateId());
        template.setSpecNum(template.getSpecNum()-1);
        templateMapper.updateByPrimaryKey(template);
    }

    /*
     *构建查询条件
     *@param searchMap
     *@return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(Spec.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            //规格父id
            if(searchMap.get("templateId")!=null && !"".equals(searchMap.get("templateId"))){
                criteria. andEqualTo("templateId", searchMap.get("templateId"));
            }
            //规格名称
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            //规格选项
            if(searchMap. get("options") !=null && !"".equals(searchMap. get("options"))){
                criteria. andLike("options","%"+searchMap.get("options")+"%");
            }
        }
        return example;
    }
}
