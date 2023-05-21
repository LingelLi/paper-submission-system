package cn.lynu.controller;

import javax.servlet.http.HttpSession;

import cn.lynu.model.Status;
import cn.lynu.service.StatusService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/status")
@CrossOrigin
public class StatusController {
    @Autowired
    private StatusService StatusService;


    @ResponseBody
    @RequestMapping("/get")
    public Map getStatusByUserId (@RequestParam("user_id") String userId) {
        Map map=new HashMap<>();
        map.put("status_code",StatusService.getStatusByUserId(userId));
        System.out.println(map.get("status_code"));
        return map;

    }

    @ResponseBody
    @RequestMapping("/getall")
    public Map getStuBystatus(@Param("status_code") String status_code) {
        Map map=new HashMap<>();
        map.put("user_ids",StatusService.getStuBystatus(status_code));
        System.out.println(map);
        return map;
    }



    @ResponseBody
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public Map update(@RequestParam("user_id") String user_id, @RequestParam("status_code") String status_code)
    {
        Status status = new Status();
        status.setUser_id(user_id);
        status.setStatus(status_code);
        Map map = new HashMap<>();
        if(StatusService.updateStatusByUserId(status)>0)
        {
            map.put("status","success");
        }
        else{
            map.put("status","fail");
        }
        return map;
    }




}
