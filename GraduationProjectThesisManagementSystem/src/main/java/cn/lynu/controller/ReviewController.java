package cn.lynu.controller;

import cn.lynu.model.Review;
import cn.lynu.model.Student;
import cn.lynu.model.User;
import cn.lynu.service.ReviewService;
import cn.lynu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @ResponseBody
    @RequestMapping(value="/review1/insert")
    public Map insert1(Review record) {
        Map map = new HashMap();

        if( reviewService.insert1(record)){
            map.put("status","success");
        }
        return map;

    }
    @ResponseBody
    @RequestMapping(value="/review2/insert")
    public Map insert2(Review record) {
        Map map = new HashMap();

        if( reviewService.insert2(record)){
            map.put("status","success");
        }
        return map;
    }
    @ResponseBody
    @RequestMapping(value="/review3/insert")
    public Map insert3(Review record) {
        Map map = new HashMap();

        if( reviewService.insert3(record)){
            map.put("status","success");
        }
        return map;
    }
    @ResponseBody
    @RequestMapping(value="/review4/insert")
    public Map insert4(Review record) {
        Map map = new HashMap();

        if( reviewService.insert4(record)){
            map.put("status","success");
        }
        return map;
    }
    @ResponseBody
    @RequestMapping(value="/review5/insert")
    public Map insert5(Review record) {
        Map map = new HashMap();

        if( reviewService.insert5(record)){
            map.put("status","success");
        }
        return map;
    }
    @ResponseBody
    @RequestMapping(value="/review1/get")
    public Map get1(String user_id) {
        Map map = new HashMap();
        map=reviewService.get1(user_id);
        return map;

    }
    @ResponseBody
    @RequestMapping(value="/review2/get")
    public Map get2(String user_id) {
        Map map = new HashMap();
        map=reviewService.get2(user_id);
        return map;
    }
    @ResponseBody
    @RequestMapping(value="/review3/get")
    public Map get3(String user_id) {
        Map map = new HashMap();
        map=reviewService.get3(user_id);
        return map;
    }
    @ResponseBody
    @RequestMapping(value="/review4/get")
    public Map get4(String user_id) {
        Map map = new HashMap();
        map=reviewService.get4(user_id);
        return map;
    }

}
