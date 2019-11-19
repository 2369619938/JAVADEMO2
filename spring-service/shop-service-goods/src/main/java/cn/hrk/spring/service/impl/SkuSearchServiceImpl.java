package cn.hrk.spring.service.impl;

import cn.hrk.common.domain.ESUtil;
import cn.hrk.common.domain.R;
import cn.hrk.spring.goods.domain.Sku;
import cn.hrk.spring.goods.service.ISkuSearchService;
import cn.hrk.spring.mapper.BrandMapper;
import cn.hrk.spring.mapper.SkuMapper;
import cn.hrk.spring.mapper.SpecMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@RequestMapping("/SkuSearch")
public class SkuSearchServiceImpl implements ISkuSearchService {
    private static final ObjectMapper MAPPER=new ObjectMapper();
    private final String INDEX="sku";
    private final String TYPE="doc";
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private SpecMapper specMapper;
    @Autowired
    private SkuMapper skuMapper;
    @RequestMapping("/search")
    @Override
    public Map search(@RequestBody Map<String, String> searchMap) {
        return null;
    }
    @RequestMapping("/init")
    @ResponseBody
    public R init() {
        ESUtil esUtil=new ESUtil(restHighLevelClient);
        if (esUtil.existsIndex(INDEX)){
            esUtil.deleteIndex(INDEX);
        }
        XContentBuilder builder=null;
        try {
            builder= JsonXContent.contentBuilder().startObject()
                    .startObject(TYPE)
                    .startObject("properties")
                    .startObject("title")
                    .field("type","text")
                    .field("analyzer","ik_max_word")
                    .endObject()
                    .startObject("brand")
                    .field("type","keyword")
                    .field("store","true")
                    .endObject()
                    .startObject("id")
                    .field("type","text")
                    .field("index","false")
                    .endObject()
                    .startObject("images")
                    .field("type","text")
                    .field("index","false")
                    .endObject()
                    .startObject("category")
                    .field("type","keyword")
                    .field("index","true")
                    .endObject()
                    .endObject()
                    .endObject()
                    .endObject();
            System.out.println(builder.toString());
            esUtil.createIndexAndMapping(INDEX, TYPE, builder, new ActionListener<CreateIndexResponse>() {
                @Override
                public void onResponse(CreateIndexResponse createIndexResponse) {
                    System.out.println("索引创建失败");
                    boolean acknowledged=createIndexResponse.isAcknowledged();
                    boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();
                    if (acknowledged && shardsAcknowledged){
                        List<Sku> list=skuMapper.selectAll();
                        List<Map<String,Object>> skuMapList=getItems(list);
                        esUtil.bulkDate(INDEX,TYPE,"id",skuMapList);
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    System.out.println("索引创建失败");
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok();
    }
    public List<Map<String,Object>> getItems(List<Sku> list){
        List<Map<String,Object>> listMap=new ArrayList<>();
        for (Sku sku:list){
            Map<String,Object> map=new HashMap<>();
            map.put("id",sku.getId());
            map.put("title",sku.getName());
            map.put("category",sku.getCategoryName());
            map.put("brand",sku.getBrandName());
            map.put("images",sku.getImages());
            map.put("price",sku.getPrice());
            listMap.add(map);
        }
        return  listMap;
    }
}
