package cn.lynu.mapper;

import cn.lynu.model.Degree;
import cn.lynu.model.Degree;
import java.util.List;

import cn.lynu.model.Student;
import org.apache.ibatis.annotations.Param;

public interface DegreeMapper {
    int insert(Degree record);

    int insertSelective(Degree record);

    Degree getDegreeByUserId(String  user_id);

//    int updateDegreeByUserId(@Param("user_id")String user_id,@Param("graduate_time")String graduate_time,@Param("graduate_to")String graduate_to,@Param("graduate_to_local")String graduate_to_local
//            ,@Param("factory_nature")String factory_nature,@Param("work_nature")String work_nature,@Param("before_education")String before_education,@Param("bachelor_time")String bachelor_time,
//                             @Param("bachelor_school")String bachelor_school,@Param("reward_punish")String reward_punish,@Param("apply_reason")String apply_reason,
//                             @Param("research")String research,@Param("experience")String experience,@Param("paper_title")String paper_title,
//                             @Param("paper_type")String paper_type,@Param("paper_from")String paper_from,@Param("paper_describe")String paper_describe,
//                             @Param("cv_url")String cv_url);

    int updateDegreeByUserId(Degree degree);


}
