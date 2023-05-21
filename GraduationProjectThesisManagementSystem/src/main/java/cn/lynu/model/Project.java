package cn.lynu.model;

import java.io.Serializable;

public class Project implements Serializable{
	private static final long serialVersionUID = -7818290024619870851L;
	
	private Integer projectId;
    private  String user_id;
    private String opening_report_url;

    public String getOpening_report_url() {
        return opening_report_url;
    }

    public void setOpening_report_url(String opening_report_url) {
        this.opening_report_url = opening_report_url;
    }

    public String getPaper_url() {
        return paper_url;
    }

    public void setPaper_url(String paper_url) {
        this.paper_url = paper_url;
    }

    private String paper_url;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    private String projectName;
    private String projectEngName;

    public String getProjectEngName() {
        return projectEngName;
    }

    public void setProjectEngName(String projectEngName) {
        this.projectEngName = projectEngName;
    }

    public String getProjectKeyWords() {
        return projectKeyWords;
    }

    public void setProjectKeyWords(String projectKeyWords) {
        this.projectKeyWords = projectKeyWords;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    private String projectKeyWords;
    private String projectType;


    private String projectDescribe;

    private Integer projectFromId;

    private String teacherId;

    private String studentId;
    private String openingRepore;
    private String projectFrom;

    public String getProjectFrom() {
        return projectFrom;
    }

    public void setProjectFrom(String projectFrom) {
        this.projectFrom = projectFrom;
    }

    public String getOpeningRepore() {
        return openingRepore;
    }

    public void setOpeningRepore(String openingRepore) {
        this.openingRepore = openingRepore;
    }

    private Teacher teacher;
    private ProjectFrom projectFrom1;
    private Student student;
    
    public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getProjectDescribe() {
        return projectDescribe;
    }

    public void setProjectDescribe(String projectDescribe) {
        this.projectDescribe = projectDescribe == null ? null : projectDescribe.trim();
    }

    public Integer getProjectFromId() {
        return projectFromId;
    }

    public void setProjectFromId(Integer projectFromId) {
        this.projectFromId = projectFromId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public ProjectFrom getProjectFrom1() {
		return projectFrom1;
	}

	public void setProjectFrom1(ProjectFrom projectFrom) {
		this.projectFrom1 = projectFrom;
	}
    
}