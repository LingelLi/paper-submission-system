package cn.lynu.model;

import java.io.Serializable;

public class Student implements Serializable{
    private static final long serialVersionUID = 6462341393652342296L;
    private String area;
    private String telephone;

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTeacher_institution() {
        return teacher_institution;
    }

    public void setTeacher_institution(String teacher_institution) {
        this.teacher_institution = teacher_institution;
    }

    public String getTeacher_major() {
        return teacher_major;
    }

    public void setTeacher_major(String teacher_major) {
        this.teacher_major = teacher_major;
    }

    private String teacher_name;
    private String teacher_institution;
    private String teacher_major;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getIn_time() {
        return in_time;
    }

    public void setIn_time(String in_time) {
        this.in_time = in_time;
    }

    public String getLearn_period() {
        return learn_period;
    }

    public void setLearn_period(String learn_period) {
        this.learn_period = learn_period;
    }

    public String getAdmission_category() {
        return admission_category;
    }

    public void setAdmission_category(String admission_category) {
        this.admission_category = admission_category;
    }

    private String nation;
    private String birthday;
    private String card_type;
    private String card_number;
    private String department;
    private String major;
    private String direction;
    private String in_time;
    private String learn_period;
    private String admission_category;

    private String studentId;

    private Integer projectNum;

    private Integer goodBoy;

    private Integer projectId;

    private String teacherId;

    private String userId;

    private Integer classId;

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    private Integer studentScore;

    private String teacherEvaluate;

    private String yansouTeamId;

    private  String status_code;

    //级联属性
    private User user;
    private Project project;
    private ClassInfo classInfo;
    private Teacher teacher;
    private Ktbg ktbg;
    private Zqjc zqjc;
    private Mdb mdb;
    private Lunwen lunwen;
    private DaBian daBian;



    public DaBian getDaBian() {
        return daBian;
    }

    public void setDaBian(DaBian daBian) {
        this.daBian = daBian;
    }

    public Lunwen getLunwen() {
        return lunwen;
    }

    public void setLunwen(Lunwen lunwen) {
        this.lunwen = lunwen;
    }

    public Mdb getMdb() {
        return mdb;
    }

    public void setMdb(Mdb mdb) {
        this.mdb = mdb;
    }

    public String getYansouTeamId() {
        return yansouTeamId;
    }

    public void setYansouTeamId(String yansouTeamId) {
        this.yansouTeamId = yansouTeamId;
    }

    public Zqjc getZqjc() {
        return zqjc;
    }

    public void setZqjc(Zqjc zqjc) {
        this.zqjc = zqjc;
    }

    public Ktbg getKtbg() {
        return ktbg;
    }

    public void setKtbg(Ktbg ktbg) {
        this.ktbg = ktbg;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public ClassInfo getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(ClassInfo classInfo) {
        this.classInfo = classInfo;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public Integer getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(Integer projectNum) {
        this.projectNum = projectNum;
    }

    public Integer getGoodBoy() {
        return goodBoy;
    }

    public void setGoodBoy(Integer goodBoy) {
        this.goodBoy = goodBoy;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }


    public Integer getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(Integer studentScore) {
        this.studentScore = studentScore;
    }

    public String getTeacherEvaluate() {
        return teacherEvaluate;
    }

    public void setTeacherEvaluate(String teacherEvaluate) {
        this.teacherEvaluate = teacherEvaluate == null ? null : teacherEvaluate.trim();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}