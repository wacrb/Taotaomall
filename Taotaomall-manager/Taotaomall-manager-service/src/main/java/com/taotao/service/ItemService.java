package com.taotao.service;

import com.taotao.common.result.EUDataGridResult;
import com.taotao.common.result.TaotaoResult;
import com.taotao.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemService {

//增
    TaotaoResult saveItem(TbItem item, String desc, String itemParams) throws Exception;
    TaotaoResult insertItemDesc(Long id, String desc) throws Exception;
    TaotaoResult insertItemParamItem(Long itemId, String itemParam)throws Exception;
//查
    EUDataGridResult getItemList(int page, int rows);
    TaotaoResult getItemById(Long id);
    TaotaoResult queryItemDesc(Long id)throws Exception;
    TaotaoResult queryItemParamItem(Long id)throws Exception;
//改
    TaotaoResult updateItem(TbItem item, String desc, String itemParams)throws Exception;
    TaotaoResult updateItemDesc(Long itemId, String desc) throws Exception;
    TaotaoResult updateItemParamItem(Long itemId, String itemParam) throws Exception;
//删
    TaotaoResult deleteItem(Long ids)throws Exception;
    TaotaoResult deleteItemDesc(Long itemId) throws Exception;
    TaotaoResult deleteItemParamItem(Long itemId) throws Exception;


}
