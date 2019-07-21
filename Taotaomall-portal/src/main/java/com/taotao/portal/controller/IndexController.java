package com.taotao.portal.controller;

import com.taotao.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    ContentService contentService;

    /**
     * 打开首页
     */
    @RequestMapping("/index")
    public String showIndex(Model model) {
        String adJson = contentService.getContentList();
        model.addAttribute("ad1", adJson);

        return "index";
    }
    @RequestMapping("/httpclient/post")
    @ResponseBody
    public String testPost(String username,String password) {
        String result = "u:"+username+"p:"+password;
        System.out.println(result);
        return result;
    }


}
