package cn.lynu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.lynu.model.Project;
import cn.lynu.model.Teacher;
import cn.lynu.model.User;
import cn.lynu.service.ProjectService;
import cn.lynu.service.StudentService;
import cn.lynu.service.TeacherService;

@CrossOrigin
@Controller
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private StudentService studentService;
	
	@ResponseBody
	@RequestMapping(value="/getProjectListByTeacherId",method=RequestMethod.GET)
	public PageInfo<Project> getProjectListByTeacherId(@RequestParam(required=true)String teacherId,
			  @RequestParam(defaultValue="1")int pageNum,@RequestParam(defaultValue="8")int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Project> list = projectService.getProjectListByTeacherId(teacherId);
		return new PageInfo<>(list);
	}
	
	@ResponseBody
	@RequestMapping(value="/getCountProjectNum",method=RequestMethod.GET)
	public int getCountProjectNum() {
		return projectService.getCountProjectNum();
	}



	
	@ResponseBody
	@RequestMapping(value="/thisTeacherYesProjectNum",method=RequestMethod.GET)
	public int thisTeacherYesProjectNum(HttpSession session) {
		User user=(User) session.getAttribute("user");
		if(user!=null) {
			Teacher teacher = teacherService.findTeacherByUserId(user.getUserId());
			if(teacher!=null) {
				String teacherId = teacher.getTeacherId();
				return projectService.thisTeacherYesProjectNum(teacherId);
			}
		}
		return 0;
	}
	
	@ResponseBody
	@RequestMapping(value="/thisTeacherUndefinedStudentNum",method=RequestMethod.GET)
	public int thisTeacherUndefinedStudentNum(HttpSession session) {
		User user=(User) session.getAttribute("user");
		if(user!=null) {
			Teacher teacher = teacherService.findTeacherByUserId(user.getUserId());
			if(teacher!=null) {
				String teacherId = teacher.getTeacherId();
				return studentService.thisTeacherUndefinedStudentNum(teacherId);
			}
		}
		return 0;
	}
	
	/**
	 * 添加与更新方法
	 * @param project
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insertSelective",method=RequestMethod.POST)
	public boolean insertSelective(Project project,HttpSession session) {
		User user=(User) session.getAttribute("user");
		if(user!=null) {
			Teacher teacher = teacherService.findTeacherByUserId(user.getUserId());
			if(teacher!=null) {
				project.setTeacherId(teacher.getTeacherId());
				if(project.getProjectId()!=null) {
					return projectService.updateByPrimaryKeySelective(project);
				}else {
					return projectService.insertSelective(project,teacher.getTeacherId());
				}
			}
		}
		return false;
	}
	@ResponseBody
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public Map insert(Project project,HttpSession session) {
		Map map=new HashMap<>();
		if(projectService.insert(project)>0){
			map.put("status","success");
		}
		else
			map.put("status","fail");
		return map;

	}
	@ResponseBody
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Map  update(Project project,HttpSession session) {
		Map map=new HashMap<>();
		if(projectService.update(project)>0)
			map.put("status","success");
		else
			map.put("status","fail");
		return map;
	}
	@ResponseBody
	@RequestMapping(value="/data",method=RequestMethod.POST)
	public Project update(String user_id) {
		return projectService.getProjectByUserId(user_id);

	}

	
	@ResponseBody
	@RequestMapping(value="/getProjectByTeacherId")
	public List<Project> getProjectByTeacherId(HttpSession session){
		User user=(User) session.getAttribute("user");
		if(user!=null) {
			Teacher teacher = teacherService.findTeacherByUserId(user.getUserId());
			if(teacher!=null) {
				return projectService.getProjectByTeacherId(teacher.getTeacherId());
			}
		}
		return null;
	}

}
