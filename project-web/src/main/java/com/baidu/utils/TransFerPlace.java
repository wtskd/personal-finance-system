package com.baidu.utils;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ClassUtils;

import java.io.*;
import java.net.URL;

public class TransFerPlace {


    //将文件复制到resource/templates/static/img目录
    public static void transfer(File file){
        try {
            //创建输入流
            InputStream inputStream = new FileInputStream(file);
            System.out.println("复制文件的目标文件名称："+file.getName());

            //获取target目录下的static/img目录路径
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource("classpath:static/img/");
            URL url = resource.getURL();
            //创建输出流
            OutputStream outputStream=new FileOutputStream(url.getFile()+file.getName());


            //将文件从服务器拷贝到target/static/img中
            byte b[]=new byte[100];
            int len=-1;
            while((len=inputStream.read(b,0,b.length))!=-1){
                    outputStream.write(b,0,len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
