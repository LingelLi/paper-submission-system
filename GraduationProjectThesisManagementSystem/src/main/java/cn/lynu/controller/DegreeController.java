package cn.lynu.controller;

import javax.servlet.http.HttpSession;

import cn.lynu.model.Degree;
import cn.lynu.service.DegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.lynu.model.Degree;
import cn.lynu.model.Student;
import cn.lynu.model.User;
import cn.lynu.service.DegreeService;
import cn.lynu.service.StudentService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/degree")
public class DegreeController {
    @Autowired
    private DegreeService degreeService;
    @Autowired
    private StudentService studentService;

//    @ResponseBody
//    @RequestMapping(value="/insert",method=RequestMethod.POST)
//    public Map insert(String user_id, String graduate_time, String graduate_to , String graduate_to_local, String factory_nature
//    , String work_nature, String before_education, String bachelor_time , String bachelor_school, String reward_punish, String apply_reason
//    , String research ,String experience ,String paper_title ,String paper_type,String paper_from,String paper_describe,String cv_url)
//    {
//        Map map = new HashMap<>();
//        if(degreeService.updateDegreeByUserId(user_id, graduate_time, graduate_to ,  graduate_to_local,  factory_nature
//                ,  work_nature,  before_education,  bachelor_time ,  bachelor_school,  reward_punish,  apply_reason
//                ,  research , experience , paper_title , paper_type, paper_from, paper_describe, cv_url)>0)
//        {
//            map.put("status","success");
//        }
//        else{
//            map.put("status","fail");
//        }
//        return map;
//    }


    @ResponseBody
    @RequestMapping("/get")
    public Degree getDegreeByUserId (@RequestParam("user_id") String userId) {
        Degree degree=degreeService.getDegreeByUserId(userId);
        return degree;
    }


    @ResponseBody
    @RequestMapping(value="/insert",method=RequestMethod.POST)
    public Map insert(Degree degree)
    {
        Map map = new HashMap<>();
        if(degreeService.updateDegreeByUserId(degree)>0)
        {
            map.put("status","success");
        }
        else{
            map.put("status","fail");
        }
        return map;
    }


}
