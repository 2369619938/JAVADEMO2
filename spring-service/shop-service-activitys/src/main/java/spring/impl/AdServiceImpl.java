package spring.impl;

import cn.hrk.common.domain.CacheKey;
import cn.hrk.common.domain.IdWorker;
import cn.hrk.common.domain.PageResult;
import cn.hrk.spring.activitys.domain.Ad;
import cn.hrk.spring.activitys.service.IAdService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import spring.mapper.AdMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ad")
public class AdServiceImpl implements IAdService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdServiceImpl.class);

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private AdMapper adMapper;
    IdWorker idWorker=new IdWorker();
    Date date=new Date();
    @GetMapping("/findAll")
    public List<Ad> findAll() {
        return adMapper.selectAll();
    }
    /**
     * 分⻚查询
     * @param page ⻚码
     * @param size 每⻚记录数
     * @return 分⻚结果
     */
    @GetMapping("/findPage")
    public PageResult<Ad> findPage(@RequestParam("page") int page,
                                      @RequestParam("size") int size) {
        PageHelper.startPage(page,size);
        Page<Ad> AdServics = (Page<Ad>) adMapper.selectAll();
        return new PageResult<Ad>
                (AdServics.getTotal(),AdServics.getResult());
    }
    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @PostMapping("/findList")
    public List<Ad> findList(@RequestBody Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return adMapper.selectByExample(example);
    }
    /**
     * 分⻚+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/findPage")
    public PageResult<Ad> findPage(@RequestBody Map<String, Object> searchMap, @RequestParam("page") int page, @RequestParam("size") int size)
    { PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Ad> AdServics = (Page<Ad>)
                adMapper.selectByExample(example);
        return new PageResult<Ad>
                (AdServics.getTotal(),AdServics.getResult()); }
    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @GetMapping("/findById/{id}")
    public Ad findById(@PathVariable("id") Long id) {
        return adMapper.selectByPrimaryKey(id);
    }




    /**
     * 新增
     * @param AdServics
     */
    @PostMapping("/add")
    public void add(@RequestBody Ad AdServics) {
        //AdServics.setId(idWorker.nextId()+"");
        saveAdToRedisByPosition(AdServics.getPosition());
        adMapper.insert(AdServics);
    }

    /**
     * 修改
     * @param AdServics
     */
    @PostMapping("/update")
    public void update(@RequestBody Ad AdServics) {

        String pos=adMapper.selectByPrimaryKey(AdServics.getId()).getPosition();
        adMapper.updateByPrimaryKeySelective(AdServics);
        saveAdToRedisByPosition(pos);
        if(!pos.equals(AdServics.getPosition())){
            saveAdToRedisByPosition(AdServics.getPosition());
        }
    }

    /**
     * 删除
     * @param id
     */
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id)  {
        String pos=adMapper.selectByPrimaryKey(id).getPosition();
        adMapper.deleteByPrimaryKey(id);
        saveAdToRedisByPosition(pos);
    }



    /**
     本根据位置查询广告列表
     本@param position
     * eretucn
     */
     @RequestMapping("/findByPosition")
     public List<Ad> findByPosition(@RequestParam("position") String position){
     System.out.println("从缓存中提取广告"+position);
     List<Ad> list=(List<Ad>)redisTemplate.boundHashOps(CacheKey.AD.name()).get(position);
     if(list == null ){
         list = saveAdToRedisByPosition(position);
     }
         System.out.println("123123");
     return list;
     }



    private List<Ad> saveAdToRedisByPosition(String position) {
        //查询某位置的广告列表
        Example example=new Example(Ad.class);
        Example.Criteria criteria=example. createCriteria();
        criteria.andEqualTo( "position", position) ;
        criteria.andLessThanOrEqualTo("startTime", new Date());//开始时间小于等于当前时间
        criteria.andGreaterThanOrEqualTo("endTime",new Date());//截至时间大于等于当前时间
        criteria.andEqualTo("status", "1");
        System.out.println("1111");
        List<Ad> adList = adMapper.selectByExample(example);
        System.out.println(adList.size());
        //装入缓存
        if(adList.size() > 0){
            System.out.println("123123");
            redisTemplate.boundHashOps(CacheKey.AD.name()).put(position,adList);
        }
        return adList;
    }


     /*
     *构建查询条件
     *@param searchMap
     *@return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(Ad.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            //品牌id
            if(searchMap.get("orderId")!=null ){
                criteria. andEqualTo("orderId", searchMap.get("orderId"));
            }
        }
        return example;
    }
}
