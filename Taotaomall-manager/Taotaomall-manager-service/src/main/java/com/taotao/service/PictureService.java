package com.taotao.service;

import com.taotao.common.result.PictureResult;
import org.springframework.web.multipart.MultipartFile;

public interface PictureService {
    public PictureResult uploadFile(MultipartFile uploadFile) throws Exception;
}
