package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.result.EUDataGridResult;
import com.taotao.common.result.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.*;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private TbItemParamMapper itemParamMapper;

    @Override
    public EUDataGridResult getItemParamList(Integer page, Integer rows) {
        //查询商品列表
        TbItemParamExample example = new TbItemParamExample();
        //分页处理
        PageHelper.startPage(page, rows);
        List<HashMap> list = itemParamMapper.selectByExampleMaps(example);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<HashMap> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
    //增加规格
    @Override
    public TaotaoResult saveItemParam(TbItemParam itemParam) {
        Date date = new Date();
        ///补全pojo
        itemParam.setCreated(date);
        itemParam.setUpdated(date);
        //插入到规格参数模板表
        itemParamMapper.insert(itemParam);
        return TaotaoResult.ok();
    }
    //获取规格模板
    @Override
    public TaotaoResult getItemParamByCid(Long cid) throws Exception {
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
        //判断是否查询到结果
        if (list != null && list.size() > 0) {
            return TaotaoResult.ok(list.get(0));
        }
        return TaotaoResult.ok();
    }
   //删除
    @Override
    public TaotaoResult deleteItemParam(Long ids) {
        itemParamMapper.deleteByPrimaryKey(ids);
        return TaotaoResult.ok();
    }
}
