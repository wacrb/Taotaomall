package com.taotao.controller;

import com.taotao.common.result.EUDataGridResult;
import com.taotao.common.result.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;
    //规格参数列表
    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemParamList(Integer page, Integer rows) {
        EUDataGridResult result = itemParamService.getItemParamList( page,rows) ;
        return result;
    }
    @RequestMapping("/query/itemcatid/{itemCatId}")
    public@ResponseBody  TaotaoResult getItemParamByCid(@PathVariable Long itemCatId) throws Exception {
        //获取商品规格参数模板
        TaotaoResult result = itemParamService.getItemParamByCid(itemCatId);
        return result;
    }

    //添加商品规格参数模板
    @RequestMapping("/save/{cid}")
    @ResponseBody
    public TaotaoResult saveItemparam(@PathVariable Long cid, String paramData) throws Exception {
        //创建pojo对象
        TbItemParam itemParam = new TbItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        TaotaoResult result = itemParamService.saveItemParam(itemParam);
        return result;

    }
    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public TaotaoResult deleteItem(Long ids) throws Exception {
        //删除商品信息
        itemParamService.deleteItemParam(ids);
        return TaotaoResult.ok();
    }
}
