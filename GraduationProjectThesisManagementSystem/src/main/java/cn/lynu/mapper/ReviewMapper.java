package cn.lynu.mapper;

import cn.lynu.model.Review;
import cn.lynu.model.Student;

import java.util.List;
import java.util.Map;

public interface ReviewMapper {
    int insert1(Review record);
    List<Map<String,String>> get1(String user_id);
    int insert2(Review record);
    List<Map<String,String>> get2(String user_id);
    int insert3(Review record);
    List<Map<String,String>> get3(String user_id);
    int insert4(Review record);
    List<Map<String,String>> get4(String user_id);
    int insert5(Review record);


}
