package com.taotao.controller;

import com.taotao.common.result.EUDataGridResult;
import com.taotao.common.result.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContentController {
    @Autowired
    ContentService contentService;

    @RequestMapping("/content/query/list")
    @ResponseBody
    public EUDataGridResult getContentList(Long categoryId,Integer page, Integer rows){
        EUDataGridResult result = contentService.getContentList(categoryId,page, rows);
        return result;
    }
    @RequestMapping("/content/save")
    @ResponseBody
    public TaotaoResult saveContent(TbContent content) {
        contentService.saveContent(content);
        return TaotaoResult.ok();
    }
    @RequestMapping("/content/delete")
    @ResponseBody
    public TaotaoResult deleteContent(Long ids) {
        contentService.deleteContent(ids);
        return TaotaoResult.ok();
    }
    @RequestMapping("/rest/content/update")
    @ResponseBody
    public TaotaoResult updateContent(TbContent content) {
        contentService.updateContent(content);
        return TaotaoResult.ok();
    }
}
