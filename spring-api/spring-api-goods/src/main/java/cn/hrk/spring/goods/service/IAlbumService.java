package cn.hrk.spring.goods.service;

import cn.hrk.common.domain.PageResult;
import cn.hrk.common.domain.R;
import cn.hrk.spring.goods.domain.Album;
import cn.hrk.spring.goods.domain.Album;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RequestMapping("/album")
public interface IAlbumService {
    @GetMapping("/findAll")
    public List<Album> findAll();
    @GetMapping("/findPage")
    public PageResult<Album> findPage(@RequestParam("page") int page, @RequestParam("size") int size);
    @PostMapping("/findList")
    public List<Album> findList(Map<String, Object> searchMap);
    @PostMapping("/findPage")
    public PageResult<Album> findPage(Map<String, Object> searchMap, @RequestParam("page") int page, @RequestParam("size") int size);
    @GetMapping("/findById/{id}")
    public Album findById(@PathVariable("id") Integer id);
    @PostMapping("/add")
    public void add(@RequestBody Album Album);
    @PostMapping("/update")
    public void update(@RequestBody Album Album);
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id);
}
