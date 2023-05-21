package cn.lynu.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.lynu.service.StatusService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import cn.lynu.model.Project;
import cn.lynu.model.Student;
import cn.lynu.model.User;
import cn.lynu.service.ProjectService;
import cn.lynu.service.StudentService;
import cn.lynu.util.WordUtils;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/student")
@CrossOrigin
public class StudentController {
	private static final String UPLOAD_FOLDER=System.getProperty("user.dir")+"/upload/";

	@Autowired
	private StudentService studentService;
	@Autowired
	private StatusService statusService;
	@Autowired
	private ProjectService projectService;
	@RequestMapping(value = "/upload")
	@ResponseBody
	public Map uploadPicture(@RequestParam(value="file",required=false)MultipartFile file, HttpServletRequest request, HttpServletResponse response){

		Map map=new HashMap<>();
		File targetFile=null;
		String url="";//返回存储路径
		int code=1;
		System.out.println(file);
		String fileName=file.getOriginalFilename();//获取文件名加后缀
		if(fileName!=null&&fileName!=""){

			String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/upload/";//存储路径
			String path = request.getSession().getServletContext().getRealPath("upload"); //文件存储位置
			String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
			fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名
			//先判断文件是否存在
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String fileAdd = sdf.format(new Date());
			//获取文件夹路径
			File file1 =new File(path+"/"+fileAdd);
			//如果文件夹不存在则创建
			if(!file1 .exists()  && !file1 .isDirectory()){
				file1 .mkdir();
			}
			//将图片存入文件夹
			targetFile = new File(file1, fileName);
			try {
				//将上传的文件写到服务器上指定的文件。
				file.transferTo(targetFile);
				url=returnUrl+fileAdd+"/"+fileName;
				code=0;
				System.out.println(url);
				map.put("fileurl", url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(map.get("fileurl"));
		return map;


	}

	@ResponseBody
	@RequestMapping("/hasChooseProject")
	public Student hasChooseProject(HttpSession session,HttpServletResponse response) {
		response.setContentType("application/json;charset=utf-8");
		User user=(User) session.getAttribute("user");
		if(user!=null) {
			Student student = studentService.getStudentByUserId(user.getUserId());
			if(student!=null) {
				Project project = projectService.getProjectByStudentId(student.getStudentId());
				if(project!=null) {
					return student;
				}
			}
		}
		return new Student();
	}

	@ResponseBody
	@RequestMapping(value="/updateProjectByStudentId/{projectId}",method=RequestMethod.PUT)
	public boolean updateProjectByStudentId(HttpSession session,@PathVariable("projectId")String projectId) {
		User user=(User) session.getAttribute("user");
		if(user!=null) {
			Student student = studentService.getStudentByUserId(user.getUserId());
			if(student!=null) {
				if(!student.getStudentId().isEmpty()) {
					return projectService.updateStudentIdByProjectId(student.getStudentId(), projectId);
				}
			}
		}
		return false;
	}

	@ResponseBody
	@RequestMapping(value="/updateTeacherId")
	public boolean updateTeacherId(HttpSession session,String teacherId) {
		User user=(User) session.getAttribute("user");
		if(user!=null) {
			Student student = studentService.getStudentByUserId(user.getUserId());
			if(student!=null) {
				return studentService.updateTeacherId(teacherId,student.getStudentId());
			}
		}
		return false;
	}

	@ResponseBody
	@RequestMapping("/getUserByStudentId")
	public User getUserByStudentId(String studentId) {
		return studentService.getUserByStudentId(studentId);
	}

	@ResponseBody
	@RequestMapping(value="/updateProjectNum",method=RequestMethod.PUT)
	public boolean updateProjectNum(String studentId,String teacherId,String projectId) {
		return studentService.updateProjectNum(studentId,teacherId,projectId);
	}

	@ResponseBody
	@RequestMapping("/projectNum")
	public boolean projectNum(String studentId) {
		Student student=studentService.projectNum(studentId);
		if(student!=null) {
			if(student.getProjectNum()==1) {
				return true;
			}
		}
		return false;
	}

	@ResponseBody
	@RequestMapping("/getStudentByUserId")
	public Student getStudentByUserId (@RequestParam("user_id") String userId) {
		Student student=studentService.getStudentByUserId(userId);
		System.out.println(student.getCard_number());
		return student;
	}

	@ResponseBody
	@RequestMapping("/getStudentListByTeacherIdAndStatus")
	public Map getStudentListByTeacherIdAndStatus(@RequestParam("status_code") String status_code, @RequestParam("teacher_id") String teacher_id) {
		Map map=new HashMap<>();
		map.put("user_ids",statusService.getStudentListByTeacherIdAndStatus(status_code,teacher_id));
		return map;
	}



	@ResponseBody
	@RequestMapping("/getTeacher")
	public Student getTeacher (String user_id) {
		Student student=studentService.getTeacher(user_id);

		return student;
	}
	@ResponseBody
	@RequestMapping("/insert")
	public Map insert (String user_id,String use_name,String teacher_name,String teacher_institution,String teacher_major) {
		Map map=new HashMap<>();
		if (studentService.updateStudentByUserId(user_id,teacher_name,teacher_institution,teacher_major)>0){
			map.put("status","success");
		}else{
			map.put("status","fail");
		}
		return map;
	}
	@ResponseBody
	@RequestMapping("/getstubystudentid")
	public Student getstubystudentid (String studentId) {
		Student student=studentService.projectNum(studentId);
		return student;
	}

	@ResponseBody
	@RequestMapping(value="/updateNoProjectNum",method=RequestMethod.PUT)
	public boolean updateNoProjectNum(String studentId,String projectId) {
		boolean status1 = studentService.updateNoProjectNum(studentId);
		boolean status2 = projectService.updateNoStudent(projectId);
		if(status1&&status2) {
			return true;
		}
		return false;
	}

	@ResponseBody
	@RequestMapping("/getStuAndProject")
	public Student getStuAndProject(HttpSession session,HttpServletResponse response,String studentId) {
		if(studentId!=null&&studentId!="") {
			return studentService.getStuAndProject(studentId);
		}
		User user=(User) session.getAttribute("user");
		if(user!=null) {
			Student student = studentService.getStudentByUserId(user.getUserId());
			if(student!=null) {
				if(student.getProjectId()!=null&&student.getProjectId()!=0) {
					Student newStu = studentService.getStuAndProject(student.getStudentId());
					return newStu;
				}
			}
		}
		return new Student();
	}


	@ResponseBody
	@RequestMapping("/getStuAndProjectAndTeacher")
	public Student getStuAndProjectAndTeacher(HttpSession session,HttpServletResponse response) {
		User user=(User) session.getAttribute("user");
		if(user!=null) {
			Student student = studentService.getStudentByUserId(user.getUserId());
			if(student!=null) {
				if(student.getProjectId()!=null&&student.getProjectId()!=0) {
					Student newStu = studentService.getStuAndProjectAndTeacher(student.getStudentId());
					return newStu;
				}
			}
		}
		return new Student();
	}

	@ResponseBody
	@RequestMapping("/getStudentAndKtbgBySid")
	public Student getStudentAndKtbgBySid(String studentId) {
		return studentService.getStudentAndKtbgBySid(studentId);
	}

	@ResponseBody
	@RequestMapping("/getStudentAndZqjcBySid")
	public Student getStudentAndZqjcBySid(String studentId) {
		return studentService.getStudentAndZqjcBySid(studentId);
	}

	@ResponseBody
	@RequestMapping("/findStudent")
	public Student findStudent(HttpSession session) {
		User user=(User) session.getAttribute("user");
		if(user!=null) {
			Student student = studentService.getStudentByUserId(user.getUserId());
			if(student!=null) {
				return student;
			}
		}
		return null;
	}

	@RequestMapping("/gotoTstudentscore")
	public String gotoTstudentscore(@RequestParam(required=true)String studentId) {
		return "redirect:http://localhost:8080/graduation/teacher/tstudentscore.html?studentId="+studentId;
	}

	@ResponseBody
	@RequestMapping(value="/updateScore",method=RequestMethod.PUT)
	public boolean updateScore(Student student) {
		return studentService.updateStudent(student);
	}

	@ResponseBody
	@RequestMapping("/getThisStudentScore")
	public Integer getThisStudentScore(HttpSession session) {
		User user=(User) session.getAttribute("user");
		if(user!=null) {
			Student student = studentService.getStudentByUserId(user.getUserId());
			if(student!=null) {
				return studentService.getThisStudentScore(student.getStudentId());
			}
		}
		return -1;
	}

	@ResponseBody
	@RequestMapping("/getStudentAndMdbBySid")
	public Student getStudentAndMdbBySid(String studentId) {
		return studentService.getStudentAndMdbBySid(studentId);
	}

	//下载开题报告
	@ResponseBody
	@RequestMapping(value="/downKtbg",method=RequestMethod.GET)
	public void downKtbg(HttpSession session,
						 HttpServletRequest request,HttpServletResponse response,
						 String studentId) {
		if(studentId!=null&&!studentId.isEmpty()) {
			Student stuAndKtbg = studentService.getStudentAndKtbgBySid(studentId);
			Map<String, Object> map=new HashMap<>();
			map.put("user", stuAndKtbg.getUser());
			map.put("classInfo", stuAndKtbg.getClassInfo());
			map.put("project", stuAndKtbg.getProject());
			map.put("ktbg", stuAndKtbg.getKtbg());
			try {
				WordUtils.exportMillCertificateWord(request,response,map,"开题报告.ftl","开题报告.doc");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}else {
			User user=(User) session.getAttribute("user");
			if(user!=null) {
				Student student = studentService.getStudentByUserId(user.getUserId());
				if(student!=null) {
					if(student.getProjectId()!=null&&student.getProjectId()!=0) {
						Student stuAndKtbg = studentService.getStudentAndKtbgBySid(student.getStudentId());
						Map<String, Object> map=new HashMap<>();
						map.put("user", stuAndKtbg.getUser());
						map.put("classInfo", stuAndKtbg.getClassInfo());
						map.put("project", stuAndKtbg.getProject());
						map.put("ktbg", stuAndKtbg.getKtbg());
						try {
							WordUtils.exportMillCertificateWord(request,response,map,"开题报告.ftl","开题报告.doc");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			return;
		}
	}

	//下载免答辩
	@ResponseBody
	@RequestMapping(value="/downMdb",method=RequestMethod.GET)
	public void downMdb(HttpSession session,
						HttpServletRequest request,HttpServletResponse response,
						String studentId) {
		if(studentId!=null&&!studentId.isEmpty()) {
			Student stuAndMdb = studentService.getStudentAndMdbBySid(studentId);
			Map<String, Object> map=new HashMap<>();
			map.put("user", stuAndMdb.getUser());
			map.put("classInfo", stuAndMdb.getClassInfo());
			map.put("project", stuAndMdb.getProject());
			map.put("mdb", stuAndMdb.getMdb());
			map.put("teacher", stuAndMdb.getTeacher());
			try {
				WordUtils.exportMillCertificateWord(request,response,map,"免答辩申请表.ftl","免答辩申请表.doc");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}else {
			User user=(User) session.getAttribute("user");
			if(user!=null) {
				Student student = studentService.getStudentByUserId(user.getUserId());
				if(student!=null) {
					if(student.getProjectId()!=null&&student.getProjectId()!=0) {
						Student stuAndMdb = studentService.getStudentAndMdbBySid(student.getStudentId());
						Map<String, Object> map=new HashMap<>();
						map.put("user", stuAndMdb.getUser());
						map.put("classInfo", stuAndMdb.getClassInfo());
						map.put("project", stuAndMdb.getProject());
						map.put("mdb", stuAndMdb.getMdb());
						map.put("teacher", stuAndMdb.getTeacher());
						try {
							WordUtils.exportMillCertificateWord(request,response,map,"免答辩申请表.ftl","免答辩申请表.doc");
						} catch (IOException e) {
							e.printStackTrace();
						}
						return;
					}
				}
			}
		}
	}


	//下载中期检查
	@ResponseBody
	@RequestMapping(value="/downZqjc",method=RequestMethod.GET)
	public void downZqjc(HttpSession session,
						 HttpServletRequest request,HttpServletResponse response,
						 String studentId) {
		if(studentId!=null&&!studentId.isEmpty()) {
			Student stuAndZqjc = studentService.getStudentAndZqjcBySid(studentId);
			Map<String, Object> map=new HashMap<>();
			map.put("user", stuAndZqjc.getUser());
			map.put("project", stuAndZqjc.getProject());
			map.put("zqjc", stuAndZqjc.getZqjc());
			map.put("teacher", stuAndZqjc.getTeacher());
			try {
				WordUtils.exportMillCertificateWord(request,response,map,"中期检查表.ftl","中期检查表.doc");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}else {
			User user=(User) session.getAttribute("user");
			if(user!=null) {
				Student student = studentService.getStudentByUserId(user.getUserId());
				if(student!=null) {
					if(student.getProjectId()!=null&&student.getProjectId()!=0) {
						Student stuAndZqjc = studentService.getStudentAndZqjcBySid(student.getStudentId());
						Map<String, Object> map=new HashMap<>();
						map.put("user", stuAndZqjc.getUser());
						map.put("project", stuAndZqjc.getProject());
						if(stuAndZqjc.getZqjc()!=null) {
							map.put("zqjc", stuAndZqjc.getZqjc());
						}else {
							map.put("zqjc", null);
						}
						map.put("teacher", stuAndZqjc.getTeacher());
						try {
							WordUtils.exportMillCertificateWord(request,response,map,"中期检查表.ftl","中期检查表.doc");
						} catch (IOException e) {
							e.printStackTrace();
						}
						return;
					}
				}
			}
		}
	}

}
