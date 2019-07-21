package com.taotao.rest.service;

import com.taotao.rest.pojo.CatResult;

import java.util.List;

public interface ItemCatService {
    CatResult getItemCatList();
    List<?> getCatList(long parentId);
}
