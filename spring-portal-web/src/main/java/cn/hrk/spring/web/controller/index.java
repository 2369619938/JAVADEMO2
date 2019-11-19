package cn.hrk.spring.web.controller;

import cn.hrk.spring.activitys.domain.Ad;
import cn.hrk.spring.activitys.feign.AdFeign;
import cn.hrk.spring.goods.feign.CategoryFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class index {
    @Autowired
    private AdFeign adService;
    @Autowired
    private CategoryFeign categoryService;

    @GetMapping("/index")
    public String index(Model model) {
        //得到首页广告轮播图列表
        List<Ad> LbtList = adService.findByPosition("index_lb");

        model.addAttribute ("lbt", LbtList);

        //分类导航
        List<Map> categoryList = categoryService.findCategoryTree();
        model.addAttribute("categoryList",categoryList);
        return "index";
    }
}
