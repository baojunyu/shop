package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public  Object test(){
//        Map<String ,String> map=new HashMap<>();
//        map.put("page","2");
//        map.put("limit","2");

        return "hello word";
    }
}
