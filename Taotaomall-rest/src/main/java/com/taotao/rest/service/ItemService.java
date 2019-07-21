package com.taotao.rest.service;

import com.taotao.common.result.TaotaoResult;

public interface ItemService {
    TaotaoResult getItemBaseInfo(long itemId);
    TaotaoResult getItemDescInfo(long itemId);
    TaotaoResult getItemParam(long itemId);
}
