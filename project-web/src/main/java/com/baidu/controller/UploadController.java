package com.baidu.controller;

import com.baidu.utils.R;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Controller
public class UploadController {
    @RequestMapping("/upload")
    @ResponseBody
    public R upload(MultipartFile file, HttpSession session){
        System.out.println("------upload-----");
        String name=file.getOriginalFilename();
        System.out.println("name:"+name);

        String projectRoot = System.getProperty("user.dir");
        String path = projectRoot + "/project-web/target/classes/static/img";
        System.out.println("path:"+ path);

        File file2 = new File(path);
        if (!file2.exists()){
            file2.mkdirs();
        }

        UUID uuid = UUID.randomUUID();

        String realName = uuid+name;
        System.out.println("realName:"+realName);

        File realFile = new File(file2,realName);

        try {
            file.transferTo(realFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        R r = R.right();
        r.put("data","/img/"+realName);

        return r;
    }
}
