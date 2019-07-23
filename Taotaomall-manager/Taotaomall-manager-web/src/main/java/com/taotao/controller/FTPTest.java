package com.taotao.controller;

import com.taotao.common.utils.FtpUtil;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;

public class FTPTest {
    @Test
    public void testFtpClient()throws Exception{
        //创建ftp对象
        FTPClient ftpClient=new FTPClient();
        ftpClient.connect("192.168.191.128",21);
        //打开连接
        boolean flag1=ftpClient.login("ftpuser","wacrb555");
        System.out.println(flag1);
        //上传文件
        //读取本地文件
        FileInputStream inputStream=new FileInputStream(new File("G:/2015.jpg"));
        //上传路径
        boolean flag2=ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
        System.out.println(flag2);
        ftpClient.setControlEncoding("UTF-8");
        //设置为被动模式
        ftpClient.enterLocalPassiveMode();
        //修改上传文件格式
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //设置服务器上的文件名、上传文件的输入数据流
        boolean flag=ftpClient.storeFile("2019.jpg",inputStream);
        System.out.println(flag);
        //关闭连接
        ftpClient.logout();
    }
    @Test
    public void testFtpUtils()throws Exception{
        //读取本地文件
        FileInputStream inputStream=new FileInputStream(new File("E:/nilm结果图/model2-1.png"));
        boolean flag=FtpUtil.uploadFile("192.168.191.128",21,"ftpuser","wacrb555","/home/ftpuser/www/images","/2019/06/22","hello.jpg",inputStream);
        System.out.println(flag);
    }
}
