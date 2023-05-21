package cn.lynu.model;

import java.io.Serializable;

public class DaBian implements Serializable{
	private static final long serialVersionUID = -9000649275655068218L;
	
	private Integer id;
    private String user_id;
    private  String defenseTime;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDefenseTime() {
        return defenseTime;
    }

    public void setDefenseTime(String defenseTime) {
        this.defenseTime = defenseTime;
    }

    public String getWriteBeginTime() {
        return writeBeginTime;
    }

    public void setWriteBeginTime(String writeBeginTime) {
        this.writeBeginTime = writeBeginTime;
    }

    public String getWriteEndTime() {
        return writeEndTime;
    }

    public void setWriteEndTime(String writeEndTime) {
        this.writeEndTime = writeEndTime;
    }

    public String getSecretaryName() {
        return secretaryName;
    }

    public void setSecretaryName(String secretaryName) {
        this.secretaryName = secretaryName;
    }

    private  String writeBeginTime;
    private  String writeEndTime;
    private  String secretaryName;

    private String studentId;

    private Integer dabianScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public Integer getDabianScore() {
        return dabianScore;
    }

    public void setDabianScore(Integer dabianScore) {
        this.dabianScore = dabianScore;
    }
}