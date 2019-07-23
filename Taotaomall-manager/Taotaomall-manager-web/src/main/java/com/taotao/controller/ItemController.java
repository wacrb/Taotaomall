package com.taotao.controller;

import com.taotao.common.result.EUDataGridResult;
import com.taotao.common.result.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {
    @Autowired
    ItemService itemService;


    @RequestMapping(value = "/item/{itemId}",method = RequestMethod.GET)
    public TaotaoResult getItemById(@PathVariable Long itemId) {
        TaotaoResult result= itemService.getItemById(itemId);
        return result;
    }
    //查询商品
    @RequestMapping("/item/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows) {
        EUDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }
    //表单name和pojo对象的属性一致可返回pojo对象
    @RequestMapping("/item/save")
    @ResponseBody
    public TaotaoResult saveItem(TbItem item, String desc, String itemParams) throws Exception {
        //添加商品信息
        itemService.saveItem(item, desc, itemParams);
        return TaotaoResult.ok();
    }


    @RequestMapping("/rest/item/query/item/desc/{itemId}")
    public@ResponseBody TaotaoResult queryItemDesc(@PathVariable Long itemId) throws Exception {
        //获取商品描述
        TaotaoResult result =itemService.queryItemDesc(itemId);
        return result;
    }
    @RequestMapping("/rest/item/query/item/param/item/{itemId}")
    public@ResponseBody TaotaoResult queryItemParam(@PathVariable Long itemId) throws Exception {
        //获取商品规格
        TaotaoResult result =itemService.queryItemParamItem(itemId);
        return result;
    }
    @RequestMapping("/rest/item/update")
    public@ResponseBody TaotaoResult updateItem(TbItem item, String desc, String itemParams) throws Exception {
        //更新商品信息
        itemService.updateItem(item, desc, itemParams);
        return TaotaoResult.ok();
    }
    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public TaotaoResult deleteItem(Long ids) throws Exception {
        //删除商品信息
        itemService.deleteItem(ids);
        return TaotaoResult.ok();
    }


}
