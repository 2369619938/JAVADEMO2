package cn.hrk.spring.service.impl;

import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.goods.domain.Template;
import cn.hrk.spring.goods.domain.Template;
import cn.hrk.spring.mapper.TemplateMapper;
import cn.hrk.spring.goods.service.ITemplateService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.xml.transform.Templates;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/template")
public class TemplateServiceImpl implements ITemplateService {
    @Autowired
    private TemplateMapper templateMapper;

    @GetMapping("/findAll")
    public List<Template> findAll() {
        return templateMapper.selectAll();
    }
    /**
     * 分⻚查询
     * @param page ⻚码
     * @param size 每⻚记录数
     * @return 分⻚结果
     */
    @GetMapping("/findPage")
    public PageResult<Template> findPage(@RequestParam("page") int page,
                                      @RequestParam("size") int size) {
        PageHelper.startPage(page,size);
        Page<Template> Templates = (Page<Template>) templateMapper.selectAll();
        return new PageResult<Template>
                (Templates.getTotal(),Templates.getResult());
    }
    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @PostMapping("/findList")
    public List<Template> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return templateMapper.selectByExample(example);
    }
    /**
     * 分⻚+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/findPage")
    public PageResult<Template> findPage(Map<String, Object> searchMap, @RequestParam("page") int page, @RequestParam("size") int size)
    { PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Template> templates = (Page<Template>)
                templateMapper.selectByExample(example);
        return new PageResult<Template>
                (templates.getTotal(),templates.getResult()); }
    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public Template findById(@PathVariable("id") Integer id) {
        return templateMapper.selectByPrimaryKey(id);
    }




    /**
     * 新增
     * @param template
     */
    @PostMapping("/add")
    public void add(@RequestBody Template template) {
        templateMapper.insert(template);
    }

    /**
     * 修改
     * @param template
     */
    @PostMapping("/update")
    public void update(@RequestBody Template template) {
        templateMapper.updateByPrimaryKeySelective(template);
    }

    /**
     * 删除
     * @param id
     */
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id)  {
        templateMapper.deleteByPrimaryKey(id);
    }
    
    /*
     *构建查询条件
     *@param searchMap
     *@return
     */

    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(Template.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            //品牌名称
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
        }
        return example;
    }
}
