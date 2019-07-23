package com.taotao.controller;

import com.taotao.common.result.PictureResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/pic")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile uploadFile) throws Exception {
        //调用service上传图片
        PictureResult pictureResult = pictureService.uploadFile(uploadFile);
        //easyui不兼容火狐，所以需将java对象改为json字符串
        String result= JsonUtils.objectToJson(pictureResult);
        //返回上传结果
        return result;

    }

}
