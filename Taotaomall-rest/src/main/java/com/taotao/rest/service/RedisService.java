package com.taotao.rest.service;

import com.taotao.common.result.TaotaoResult;

public interface RedisService {
    TaotaoResult syncContent(long contentCid);
    TaotaoResult syncItemCat(long parentId);
}
