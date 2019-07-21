package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.result.TaotaoResult;

import java.util.List;

public interface ContentCategoryService {
    List<EUTreeNode> getCategoryList(long parentId);
    TaotaoResult insertContentCategory(long parentId, String name);
    TaotaoResult deleteContentCategory(Long parentId, Long id);
    TaotaoResult updateContentCategory(long id, String name);
}
