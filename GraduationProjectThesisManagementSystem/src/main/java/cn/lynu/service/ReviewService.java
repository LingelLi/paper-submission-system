package cn.lynu.service;

import cn.lynu.mapper.ReviewMapper;
import cn.lynu.mapper.StudentMapper;
import cn.lynu.model.Review;
import cn.lynu.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope(value="singleton",proxyMode= ScopedProxyMode.TARGET_CLASS)
public class ReviewService {
    @Autowired
    private ReviewMapper reviewMapper;
    @Transactional(propagation= Propagation.SUPPORTS)
    public boolean insert1(Review record) {
        return reviewMapper.insert1(record)>0;
    }
    @Transactional(propagation= Propagation.SUPPORTS)
    public boolean insert2(Review record) {
        return reviewMapper.insert2(record)>0;
    }
    @Transactional(propagation= Propagation.SUPPORTS)
    public boolean insert3(Review record) {
        return reviewMapper.insert3(record)>0;
    }
    @Transactional(propagation= Propagation.SUPPORTS)
    public boolean insert4(Review record) {
        return reviewMapper.insert4(record)>0;
    }
    @Transactional(propagation= Propagation.SUPPORTS)
    public boolean insert5(Review record) {
        return reviewMapper.insert5(record)>0;
    }
    @Transactional(propagation= Propagation.SUPPORTS)
    public Map get1(String user_id) {
        List<Map<String,String>> maps=reviewMapper.get1(user_id);
        Map map = new HashMap();
        if(maps.get(0).get("reviewer").equals("0")){
            map.put("teacher_comment",maps.get(0).get("comment"));
            map.put("college_comment",maps.get(1).get("comment"));
        }else{
            map.put("teacher_comment",maps.get(1).get("comment"));
            map.put("college_comment",maps.get(0).get("comment"));
        }
        return map;

    }
    @Transactional(propagation= Propagation.SUPPORTS)
    public Map get2(String user_id) {
        List<Map<String,String>> maps=reviewMapper.get2(user_id);
        Map map = new HashMap();
        if(maps.get(0).get("reviewer").equals("0")){
            map.put("teacher_comment",maps.get(0).get("comment"));
            map.put("college_comment",maps.get(1).get("comment"));
        }else{
            map.put("teacher_comment",maps.get(1).get("comment"));
            map.put("college_comment",maps.get(0).get("comment"));
        }
        return map;

    }
    @Transactional(propagation= Propagation.SUPPORTS)
    public Map get3(String user_id) {
        List<Map<String,String>> maps=reviewMapper.get3(user_id);
        Map map = new HashMap();
        if(maps.get(0).get("reviewer")=="0"){
            map.put("teacher_comment",maps.get(0).get("comment"));
            map.put("college_comment",maps.get(1).get("comment"));
        }else{
            map.put("teacher_comment",maps.get(1).get("comment"));
            map.put("college_comment",maps.get(0).get("comment"));
        }
        return map;

    }
    @Transactional(propagation= Propagation.SUPPORTS)
    public Map get4(String user_id) {
        List<Map<String,String>> maps=reviewMapper.get4(user_id);
        Map map = new HashMap();
        if(maps.get(0).get("reviewer")=="0"){
            map.put("teacher_score",maps.get(0).get("score"));
            map.put("college_score",maps.get(1).get("score"));
            map.put("teacher_comment",maps.get(0).get("comment"));
            map.put("college_comment",maps.get(1).get("comment"));
        }else{
            map.put("teacher_score",maps.get(1).get("score"));
            map.put("college_score",maps.get(0).get("score"));
            map.put("teacher_comment",maps.get(1).get("comment"));
            map.put("college_comment",maps.get(0).get("comment"));
        }
        return map;

    }


}
