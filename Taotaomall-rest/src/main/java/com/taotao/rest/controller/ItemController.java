package com.taotao.rest.controller;

import com.taotao.common.result.TaotaoResult;
import com.taotao.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/info/{itemId}")
    @ResponseBody
    public TaotaoResult getItemBaseInfo(@PathVariable long itemId) {
        TaotaoResult result = itemService.getItemBaseInfo(itemId);
        return result;
    }
    @RequestMapping("/desc/{itemId}")
    @ResponseBody
    public TaotaoResult getItemDescInfo(@PathVariable long itemId) {
        TaotaoResult result = itemService.getItemDescInfo(itemId);
        return result;
    }
    @RequestMapping("/param/{itemId}")
    @ResponseBody
    public TaotaoResult getItemParam(@PathVariable long itemId) {
        TaotaoResult result = itemService.getItemParam(itemId);
        return result;
    }
}
