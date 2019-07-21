package com.taotao.service;

import com.taotao.common.result.EUDataGridResult;
import com.taotao.common.result.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
    EUDataGridResult getContentList(Long categoryid,Integer page, Integer rows);

    TaotaoResult saveContent(TbContent content);

    TaotaoResult deleteContent(Long ids);

    TaotaoResult updateContent(TbContent content);
}
