package cn.lynu.service;

import cn.lynu.mapper.StatusMapper;
import cn.lynu.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.lynu.mapper.StatusMapper;
import cn.lynu.model.Status;

import java.util.List;

@Service
@Scope(value="singleton",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class StatusService {

    @Autowired
    private StatusMapper StatusMapper;

    @Transactional(propagation=Propagation.REQUIRED)
    public String getStatusByUserId(String user_id)
    {
        return StatusMapper.getStatusByUserId(user_id);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public List getStuBystatus(String status_code){
        return  StatusMapper.getStuBystatus(status_code);
    }
    @Transactional(propagation=Propagation.REQUIRED)
    public List getStudentListByTeacherIdAndStatus(String status_code,String teacher_id){
        return  StatusMapper.getStudentListByTeacherIdAndStatus(status_code,teacher_id);
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public boolean insertStatus(Status Status) {
        if(StatusMapper.insertSelective(Status)>0) {
            return true;
        }
        return false;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public int updateStatusByUserId(Status Status)
    {
        return StatusMapper.updateStatusByUserId(Status);
    }

}
