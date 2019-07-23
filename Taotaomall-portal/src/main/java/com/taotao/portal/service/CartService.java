package com.taotao.portal.service;

import com.taotao.common.result.TaotaoResult;
import com.taotao.portal.pojo.CartItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CartService {
    TaotaoResult addCartItem(long itemId, Integer num,HttpServletRequest request, HttpServletResponse response);
    List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);
    TaotaoResult updateCartItemNum(long itemId, Integer newNum, HttpServletRequest request, HttpServletResponse response);
    TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);
}
