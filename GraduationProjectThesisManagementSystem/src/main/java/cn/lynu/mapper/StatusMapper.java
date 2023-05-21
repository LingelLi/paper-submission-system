package cn.lynu.mapper;

import cn.lynu.model.Status;
import cn.lynu.model.Status;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StatusMapper {
    int insert(Status record);

    int insertSelective(Status record);

    List<String> getStuBystatus(@Param("status_code") String status_code);

    List<String> getStudentListByTeacherIdAndStatus(@Param("status_code") String status_code, @Param("teacher_id") String teacher_id);

    String getStatusByUserId(String  user_id);

    int updateStatusByUserId(Status Status);


}
