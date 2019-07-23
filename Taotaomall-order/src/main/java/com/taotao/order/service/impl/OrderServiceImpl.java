package com.taotao.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.result.EUDataGridResult;
import com.taotao.common.result.TaotaoResult;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.dao.JedisClient;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TbOrderMapper orderMapper;
    @Autowired
    private TbOrderItemMapper orderItemMapper;
    @Autowired
    private TbOrderShippingMapper orderShippingMapper;
    @Autowired
    private JedisClient jedisClient;

    @Value("${ORDER_GEN_KEY}")
    private String ORDER_GEN_KEY;
    @Value("${ORDER_INIT_ID}")
    private String ORDER_INIT_ID;
    @Value("${ORDER_DETAIL_GEN_KEY}")
    private String ORDER_DETAIL_GEN_KEY;


    @Override
    public TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping) {
        //向订单表中插入记录
        //获得订单号
        String string = jedisClient.get(ORDER_GEN_KEY);
        if (StringUtils.isBlank(string)) {
            jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID);
        }
        long orderId = jedisClient.incr(ORDER_GEN_KEY);
        //补全pojo的属性
        order.setOrderId(orderId + "");
        //状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        order.setStatus(1);
        Date date = new Date();
        order.setCreateTime(date);
        order.setUpdateTime(date);
        //0：未评价 1：已评价
        order.setBuyerRate(0);
        //向订单表插入数据
        orderMapper.insert(order);
        //插入订单明细
        for (TbOrderItem tbOrderItem : itemList) {
            //补全订单明细
            //取订单明细id
            long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
            tbOrderItem.setId(orderDetailId + "");
            tbOrderItem.setOrderId(orderId + "");
            //向订单明细插入记录
            orderItemMapper.insert(tbOrderItem);
        }
        //插入物流表
        //补全物流表的属性
        orderShipping.setOrderId(orderId + "");
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
        orderShippingMapper.insert(orderShipping);

        return TaotaoResult.ok(orderId);
    }

    @Override
    public TaotaoResult changeOrderStatus(Long orderId,Integer status) {
        TbOrder order=orderMapper.selectByPrimaryKey(orderId + "");
        order.setStatus(status);
        order.setPaymentTime(new Date());
        orderMapper.updateByPrimaryKey(order);
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult getOrderList(Long userID, Integer page, Integer count) {
        TbOrderExample example=new TbOrderExample();
        TbOrderExample.Criteria criteria=example.createCriteria();
        criteria.andUserIdEqualTo(userID);
        //分页处理
        PageHelper.startPage(page, count);
        List<TbOrder> list = orderMapper.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<TbOrder> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return TaotaoResult.ok(result);
    }

    @Override
    public TaotaoResult getOrderById(Long orderId) {
        TbOrder order=orderMapper.selectByPrimaryKey(orderId + "");
        return TaotaoResult.ok(order);
    }
}

