package com.taotao.order.controller;

import com.taotao.common.result.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.order.pojo.Order;
import com.taotao.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createOrder(@RequestBody Order order) {
        try {
            TaotaoResult result = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
    @RequestMapping("/info/{orderId}")
    @ResponseBody
    public TaotaoResult getOrderById(@RequestBody Long orderId) {
        try {
            TaotaoResult result = orderService.getOrderById(orderId);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
    @RequestMapping("/list/{userID}/{page}/{count}")
    @ResponseBody
    public TaotaoResult getOrderList(@RequestBody Long userID,
                                     @RequestParam(defaultValue="1")Integer page,
                                     @RequestParam(defaultValue="60")Integer count) {
        try {
            TaotaoResult result = orderService.getOrderList(userID,page,count);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
    @RequestMapping("/changeStatus")
    @ResponseBody
    public TaotaoResult changeOrderStatus(@RequestBody Integer status,@RequestBody Long orderId) {
        try {
            TaotaoResult result = orderService.changeOrderStatus(orderId,status);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}

