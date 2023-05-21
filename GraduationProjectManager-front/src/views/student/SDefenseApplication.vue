<template>
    <div>
        <a-card style="margin-top: 24px" :bordered="false" title="评阅答辩申请">

            <div v-if="status_code >= 11" style="margin-left: 85px;margin-bottom: 30px; font-size: larger; color:green">答辩申请已提交</div>

            <a-form :model="studentData" :label-col="labelCol" :wrapper-col="wrapperCol">
                <a-form-item label="学号">
                    <a-input :value="studentData.studentId" disabled></a-input>
                </a-form-item>
                <a-form-item label="论文题目">
                    <a-input :value="projectData.projectName" :disabled="status_code!=6 && status_code!=9"></a-input>
                </a-form-item>
                <a-form-item label="论文关键词">
                    <a-input :value="projectData.projectKeyWords" :disabled="status_code!=6 && status_code!=9"></a-input>
                </a-form-item>
                <a-form-item label="论文答辩时间">
                    <a-date-picker :value="projectData.defenseTime" @change="defenceChange" :disabled="status_code!=6 && status_code!=9"></a-date-picker>
                </a-form-item>
                <a-form-item label="论文撰写开始时间">
                    <a-date-picker :value="projectData.writeBeginTime" @change="beginTimeChange" :disabled="status_code!=6 && status_code!=9"></a-date-picker>
                </a-form-item>
                <a-form-item label="论文撰写结束时间">
                    <a-date-picker :value="projectData.writeEndTime" @change="endTimeChange" :disabled="status_code!=6 && status_code!=9"></a-date-picker>
                </a-form-item>
                <a-form-item label="导师工作单位">
                    <a-input :value="teacherData.teacher_institution" :disabled="status_code!=6 && status_code!=9"></a-input>
                </a-form-item>
                <a-form-item label="导师从事学科专业">
                    <a-input :value="teacherData.teacher_major" :disabled="status_code!=6 && status_code!=9"></a-input>
                </a-form-item>
                <a-form-item label="导师职称">
                    <a-input :value="teacherData.teacher_title" :disabled="status_code!=6 && status_code!=9"></a-input>
                </a-form-item>
                <a-form-item label="答辩秘书姓名">
                    <a-input :value="secretaryData.name" placeholder="请咨询院系后填写" :disabled="status_code!=6 && status_code!=9"></a-input>
                </a-form-item>
                <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
                    <a-button type="primary" @click="save" :disabled="status_code!=6 && status_code!=9">保存</a-button>
                </a-form-item>

            </a-form>
        </a-card>

    </div>
</template>

<script>
import {
    requestCurrentStudentData,
    requestStudentByUserId,
    requestClassName,
} from "@/api/student.js";
import { requestProjectById } from "@/api/project.js";
import moment from 'moment';
export default {
    name: "SDefenseApplication",
    data() {
        return {
            studentData: {
                studentId: "100001",
            },
            studentName: this.$store.state.userInfo.userName,
            projectData: {
                projectId: "100001",
                projectName: "ChatGPT背后的人工智能大模型的技术影响及应用展望",
                projectEngName: "haha",
                projectKeyWords: "keyword1,keyword2",
                projectType: "0",
                projectFrom: "",
                projectDescribe: ".................",
                defenseTime: "2023-05-06",
                writeBeginTime: "2023-04-01",
                writeEndTime: "2023-05-01",
            },
            teacherData: {
                teacher_name: "陈良育",
                teacher_institution: "华东师范大学",
                teacher_major: "软件工程",
                teacher_title: "副教授",
            },
            secretaryData: {
                name: "张文清",
            },
            labelCol: {
                span: 4,
            },
            wrapperCol: {
                span: 14,
            },
            status_code: 6,  //or 9
        };
    },
    created() {
        //this.getStudentData();
    },
    methods: {
        async getStudentData() {
            const result = await requestCurrentStudentData();
            this.studentData = result.data.data;
            if (
                (this.studentData.projectId != null) &
                (this.studentData.projectId !== "")
            ) {
                requestProjectById(this.studentData.projectId).then(
                    (response) => {
                        this.projectName = response.data.data.projectName;
                    }
                );
            } else {
                this.projectName = "未选择选题";
            }
            const result2 = await requestClassName(this.studentData.studentId);
            this.className = result2.data.data;
        },
        defenceChange(val) {
            this.projectData.defenseTime = moment(val).format("YYYY-MM-DD")
        },
        beginTimeChange(val) {
            this.projectData.writeBeginTime = moment(val).format("YYYY-MM-DD")
        },
        endTimeChange(val) {
            this.projectData.writeEndTime = moment(val).format("YYYY-MM-DD")
        },
        save() {
            this.status_code = 11
            //TODO
        },
        
    },
};
</script>

<style scoped>
</style>
