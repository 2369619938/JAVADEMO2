package cn.hrk.spring.goods.service;

import cn.hrk.common.domain.R;

import java.util.Map;

public interface ISkuSearchService {
    public Map search(Map<String,String> stringMap);

    public R init();
}
