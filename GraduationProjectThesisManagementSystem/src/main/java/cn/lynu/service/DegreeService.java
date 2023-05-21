package cn.lynu.service;

import cn.lynu.mapper.DegreeMapper;
import cn.lynu.model.Degree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.lynu.mapper.DegreeMapper;
import cn.lynu.model.Degree;

@Service
@Scope(value="singleton",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class DegreeService {

    @Autowired
    private DegreeMapper degreeMapper;

    @Transactional(propagation=Propagation.REQUIRED)
    public Degree getDegreeByUserId(String user_id)
    {
        return degreeMapper.getDegreeByUserId(user_id);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public boolean insertDegree(Degree Degree) {
        if(degreeMapper.insertSelective(Degree)>0) {
            return true;
        }
        return false;
    }
    //    @Transactional(propagation=Propagation.REQUIRED)
//    public int updateDegreeByUserId(String user_id, String graduate_time, String graduate_to , String graduate_to_local, String factory_nature
//            , String work_nature, String before_education, String bachelor_time , String bachelor_school, String reward_punish, String apply_reason
//            , String research ,String experience ,String paper_title ,String paper_type,String paper_from,String paper_describe,String cv_url)
//    {
//        return degreeMapper.updateDegreeByUserId(user_id, graduate_time, graduate_to ,  graduate_to_local,  factory_nature
//                ,  work_nature,  before_education,  bachelor_time ,  bachelor_school,  reward_punish,  apply_reason
//                ,  research , experience , paper_title , paper_type, paper_from, paper_describe, cv_url);
//    }
    @Transactional(propagation=Propagation.REQUIRED)
    public int updateDegreeByUserId(Degree degree)
    {
        return degreeMapper.updateDegreeByUserId(degree);
    }

}
