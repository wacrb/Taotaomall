package com.taotao.portal.service;

import com.taotao.common.result.TaotaoResult;
import com.taotao.portal.pojo.CartItem;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CartService {
    TaotaoResult addCartItem(long itemId, int num,HttpServletRequest request, HttpServletResponse response);
    List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);
    TaotaoResult updateCartItemNum(@PathVariable Long itemId, @PathVariable int newNum, HttpServletRequest request, HttpServletResponse response);
    TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);
}
