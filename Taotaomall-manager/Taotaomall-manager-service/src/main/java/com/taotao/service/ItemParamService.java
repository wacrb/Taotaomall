package com.taotao.service;

import com.taotao.common.result.EUDataGridResult;
import com.taotao.common.result.TaotaoResult;
import com.taotao.pojo.TbItemParam;

public interface ItemParamService {
    //查
    EUDataGridResult getItemParamList(Integer page, Integer rows);
    TaotaoResult getItemParamByCid(Long itemCatId)throws Exception;
    //增
    TaotaoResult saveItemParam(TbItemParam itemParam);
    //删
    TaotaoResult deleteItemParam(Long ids);
}
