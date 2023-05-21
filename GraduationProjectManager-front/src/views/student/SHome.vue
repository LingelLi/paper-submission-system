<template>
    <div>
        <a-card :bordered="false" title="流程进度">
            <a-steps :current="currentStep" progressDot>
                <a-step title="个人基本信息确认"></a-step>
                <a-step title="论文预审"></a-step>
                <a-step title="论文初评" />
                <a-step title="论文复评" />
                <a-step title="答辩申请" />
                <a-step title="答辩完成" />
                <a-step title="学位申请" />
            </a-steps>
        </a-card>

        <br />
        <a-card style="margin-top: 24px" :bordered="false" title="个人信息">
            <a-descriptions>
                <a-descriptions-item label="学号">{{studentData.studentId}}</a-descriptions-item>
                <a-descriptions-item label="姓名">{{curUserName}}</a-descriptions-item>
                <a-descriptions-item label="性别"></a-descriptions-item>
                <a-descriptions-item label="学院"></a-descriptions-item>
                <a-descriptions-item label="专业"></a-descriptions-item>
                <a-descriptions-item label="联系方式">{{curUserTel}}</a-descriptions-item>
            </a-descriptions>
            <a-descriptions title="论文信息">
                <div v-if="status_code == 3 || status_code >= 5">
                <a-descriptions-item label="论文题目"></a-descriptions-item>
                <a-descriptions-item label="指导教师">{{teacherName}}</a-descriptions-item>
                <a-descriptions-item></a-descriptions-item>
                <a-descriptions-item label="摘要"></a-descriptions-item>
                </div>
                <div v-if="status_code <= 2 || status_code == 4">
                  <a-empty description="暂无数据"></a-empty>
                </div>
            </a-descriptions>
            <!--
      <a-card type="inner" title="多层信息组">
        <a-descriptions title="组名称" size="small">
          <a-descriptions-item label="负责人">林东东</a-descriptions-item>
          <a-descriptions-item label="角色码">1234567</a-descriptions-item>
          <a-descriptions-item label="所属部门">XX公司-YY部</a-descriptions-item>
          <a-descriptions-item label="过期时间">2018-08-08</a-descriptions-item>
          <a-descriptions-item label="描述">这段描述很长很长很长很长很长很长很长很长很长很长很长很长很长很长...</a-descriptions-item>
        </a-descriptions>
        <a-divider style="margin: 16px 0" />
        <a-descriptions title="组名称" size="small" :col="1">
          <a-descriptions-item label="学名">	Citrullus lanatus (Thunb.) Matsum. et Nakai一年生蔓生藤本；茎、枝粗壮，具明显的棱。卷须较粗..</a-descriptions-item>
        </a-descriptions>
        <a-divider style="margin: 16px 0" />
        <a-descriptions title="组名称" size="small" :col="2">
          <a-descriptions-item label="负责人">付小小</a-descriptions-item>
          <a-descriptions-item label="角色码">1234567</a-descriptions-item>
        </a-descriptions>
      </a-card>
            -->
        </a-card>
    </div>
</template>

<script>
import {
    requestCurrentStudentData,
    requestStudentByUserId,
} from "@/api/student.js";
import { requestProjectById,requestProjectList } from "@/api/project.js";
import { requestNameByTeacherId } from "@/api/teacher.js";
export default {
    name: "SHome",
    data() {
        return {
            curUserId: "",
            curUserName: "",
            curUserTel: "",
            currentStep: 0,
            studentData: [],
            projectData: [],
            teacherName: "",
            have_paper: false,
            status_code: 0,
        };
    },
    created() {
        this.curUserId = this.$store.state.userInfo.userId;
        this.curUserName = this.$store.state.userInfo.userName;
        this.curUserTel = this.$store.state.userInfo.userTel;
        this.initData();
    },
    methods: {
        initData() {
            requestCurrentStudentData()
                .then((response) => {
                    this.studentData = response.data.data;
                    if (this.studentData.projectId == null) {
                        this.currentStep = 0;
                        this.projectData = null;
                    } else {
                        this.getProjectData();
                    }
                })
                .catch((err) => {});

             
        },
        getProjectData() {
            requestProjectById(this.studentData.projectId)
                .then((response) => {
                    this.projectData = response.data.data;
                    this.getNameByTeacherId(this.projectData.teacherId);
                    if (this.projectData.projectState == 1) {
                        this.currentStep = 1;
                    } else if (this.projectData.projectState == 2) {
                        this.currentStep = 2;
                    }
                })
                .catch((err) => {});
        },
        getNameByTeacherId(teacherId) {
            requestNameByTeacherId(teacherId).then((res) => {
                this.teacherName = res.data.data;
            });
        },
        setCurrentStep() {
            if(this.status_code == 0){
                this.currentStep = 0
            }
            else if(this.status_code == 1 || this.status_code == 2 || this.status_code == 4){
                this.currentStep = 1
            }
            else if(this.status_code == 3 || this.status_code == 5){
                this.currentStep = 2
            }
            else if(this.status_code == 7 || this.status_code == 8 || this.status_code == 10){
                this.currentStep = 3
            }
            else if(this.status_code == 6 || this.status_code == 9){
                this.currentStep = 4
            }
            else if(this.status_code == 11 || this.status_code == 13){
                this.currentStep = 5
            }
            else if(this.status_code == 12 || this.status_code == 14 || this.status_code == 15){
                this.currentStep = 6
            }
        }
    },
};
</script>

<style scoped></style>
