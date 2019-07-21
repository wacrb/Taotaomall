package com.taotao.portal.controller;

import com.taotao.common.result.TaotaoResult;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/add/{itemId}")
    public String addCartItem(@PathVariable Long itemId, @RequestParam(defaultValue="1")Integer num,HttpServletRequest request, HttpServletResponse response) {
        TaotaoResult result = cartService.addCartItem(itemId, num, request, response);
        return "cartSuccess";
    }
    @RequestMapping("/cart")
    public String showCart(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<CartItem> list = cartService.getCartItemList(request, response);
        model.addAttribute("cartList", list);
        return "cart";
    }
    @RequestMapping("/update/num/{itemId}/{newNum}")
    public String updateCartItemNum(@PathVariable Long itemId, @RequestParam(defaultValue="1")Integer newNum,HttpServletRequest request, HttpServletResponse response) {
        cartService.updateCartItemNum(itemId,newNum, request, response);
        return "redirect:/cart/cart.html";
    }
    @RequestMapping("/delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
        cartService.deleteCartItem(itemId, request, response);
        return "redirect:/cart/cart.html";
    }


}

