package com.taotao.order.service;

import com.taotao.common.result.TaotaoResult;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

import java.util.List;

public interface OrderService {
    TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);

    TaotaoResult changeOrderStatus(Long orderId,Integer status);

    TaotaoResult getOrderList(Long userID,Integer page,Integer count);

    TaotaoResult getOrderById(Long orderId);
}
