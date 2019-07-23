package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.result.EUDataGridResult;
import com.taotao.common.result.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.*;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;
    @Override
    public TaotaoResult getItemById(Long id) {

        //TbItem item = itemMapper.selectByPrimaryKey(id);
        //添加查询条件
        //System.out.println("hello");
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        List<TbItem> list = itemMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return TaotaoResult.ok(list.get(0));
        }
        return TaotaoResult.ok();
    }
    @Override
    public EUDataGridResult getItemList(int page, int rows) {
        //查询商品列表
        TbItemExample example = new TbItemExample();
        //分页处理
        PageHelper.startPage(page, rows);
        List<TbItem> list = itemMapper.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;

    }
    public TaotaoResult saveItem(TbItem item, String desc, String itemParams) throws Exception{
        Date date = new Date();
        //获得商品id
        long id = IDUtils.genItemId();
        //添加商品信息
        item.setId(id);
        //商品状态，1-正常，2-下架，3-删除
        item.setStatus((byte) 1);
        item.setCreated(date);
        item.setUpdated(date);
        itemMapper.insert(item);
        //添加商品描述信息
        TaotaoResult result1 = insertItemDesc(id, desc);
        if (result1.getStatus() != 200) {
            throw new Exception();
        }
        //添加商品规格信息
        TaotaoResult result2 = insertItemParamItem(id, itemParams);
        if (result2.getStatus() != 200) {
            throw new Exception();
        }

        return TaotaoResult.ok();

    }
    @Override
    public TaotaoResult insertItemDesc(Long itemId, String desc) throws Exception{
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDescMapper.insert(itemDesc);
        return TaotaoResult.ok();
    }
    //增加商品规格项
    @Override
    public TaotaoResult insertItemParamItem(Long itemId, String itemParam) throws Exception{
        Date date = new Date();
        //创建一个pojo
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParam);
        itemParamItem.setCreated(date);
        itemParamItem.setUpdated(date);
        //向表中插入数据
        itemParamItemMapper.insert(itemParamItem);

        return TaotaoResult.ok();

    }

    @Override
    public TaotaoResult queryItemDesc(Long id) throws Exception{
        TbItemDescExample example = new TbItemDescExample();
        TbItemDescExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(id);
        List<TbItemDesc> list = itemDescMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            return TaotaoResult.ok(list.get(0));
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult queryItemParamItem(Long id) throws Exception{
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(id);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            return TaotaoResult.ok(list.get(0));
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult updateItem(TbItem item, String desc, String itemParams) throws Exception {
        Date date = new Date();
        item.setUpdated(date);
        itemMapper.updateByPrimaryKey(item);
        //修改商品描述信息
        TaotaoResult result1 = updateItemDesc(item.getId(), desc);
        if (result1.getStatus() != 200) {
            throw new Exception();
        }
        //修改商品规格信息
        TaotaoResult result2 = updateItemParamItem(item.getId(), itemParams);
        if (result2.getStatus() != 200) {
            throw new Exception();
        }

        return TaotaoResult.ok();

    }
    //修改商品描述信息
    @Override
    public TaotaoResult updateItemDesc(Long itemId, String desc) throws Exception{
        TbItemDescExample example = new TbItemDescExample();
        TbItemDescExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemDesc> list = itemDescMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            TbItemDesc itemDesc=list.get(0);
            itemDesc.setItemDesc(desc);
            itemDesc.setUpdated(new Date());
            itemDescMapper.updateByPrimaryKey(itemDesc);
        }

        return TaotaoResult.ok();
    }
    //修改商品规格项
    @Override
    public TaotaoResult updateItemParamItem(Long itemId, String itemParam) throws Exception{
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            TbItemParamItem itemParamItem=list.get(0);
            itemParamItem.setParamData(itemParam);
            itemParamItem.setUpdated(new Date());
            //向表中插入数据
            itemParamItemMapper.updateByPrimaryKey(itemParamItem);
        }
        return TaotaoResult.ok();

    }

    @Override
    public TaotaoResult deleteItem(Long ids) throws Exception{
        itemMapper.deleteByPrimaryKey(ids);
        //删除商品描述信息
        TaotaoResult result1 = deleteItemDesc(ids);
        if (result1.getStatus() != 200) {
            throw new Exception();
        }
        //删除商品规格信息
        TaotaoResult result2 = deleteItemParamItem(ids);
        if (result2.getStatus() != 200) {
            throw new Exception();
        }

        return TaotaoResult.ok();

    }
    //删除商品描述信息
    @Override
    public TaotaoResult deleteItemDesc(Long itemId) throws Exception{
        TbItemDescExample example = new TbItemDescExample();
        TbItemDescExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        itemDescMapper.deleteByExample(example);
        return TaotaoResult.ok();
    }
    //删除商品规格项
    @Override
    public TaotaoResult deleteItemParamItem(Long itemId) throws Exception{
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        itemParamItemMapper.deleteByExample(example);
        return TaotaoResult.ok();

    }
}
