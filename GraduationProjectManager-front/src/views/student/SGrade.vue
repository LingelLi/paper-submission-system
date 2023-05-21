<template>
    <div>
        <a-card style="margin-top: 24px" :bordered="false" title="我的成绩">
            <div v-if="status_code <= 11" style="margin-left: 85px;margin-bottom: 30px; font-size: larger;">暂无成绩信息</div>
            <div v-if="status_code >= 12">
                <a-descriptions bordered>
                    <a-descriptions-item label="姓名">{{studentName}}</a-descriptions-item>
                    <a-descriptions-item label="学号">{{studentData.studentId}}</a-descriptions-item>
                    <a-descriptions-item label="指导教师">{{teacherData.teacherName}}</a-descriptions-item>
                    <a-descriptions-item label="论文题目" :span="3">{{ projectData.projectName }}</a-descriptions-item>
                    <a-descriptions-item label="论文创新点和主要内容" :span="3">{{ projectData.projectDescribe }}</a-descriptions-item>
                </a-descriptions>
                <a-descriptions bordered style="margin-top: 20px">
                    <a-descriptions-item label="答辩老师1点评" :span="3">{{paperResult.review1.comment}}</a-descriptions-item>
                    <a-descriptions-item label="答辩老师1打分" :span="3">{{paperResult.review1.score}}</a-descriptions-item>
                </a-descriptions>
                <a-descriptions bordered style="margin-top: 20px">
                    <a-descriptions-item label="答辩老师2点评" :span="3">{{paperResult.review1.comment}}</a-descriptions-item>
                    <a-descriptions-item label="答辩老师2打分" :span="3">{{paperResult.review1.score}}</a-descriptions-item>
                </a-descriptions>
                <a-descriptions bordered style="margin-top: 20px">
                    <a-descriptions-item label="答辩老师3点评" :span="3">{{paperResult.review1.comment}}</a-descriptions-item>
                    <a-descriptions-item label="答辩老师3打分" :span="3">{{paperResult.review1.score}}</a-descriptions-item>
                </a-descriptions>
                
                <div :wrapper-col="{ span: 14, offset: 4 }" style="margin-top: 30px;font-size: larger;">
                    综合意见：<span style="color:green" v-if="paperResult.score == '通过'">{{ paperResult.score }}</span>
                    <span style="color:red" v-if="paperResult.score == '未通过'">{{ paperResult.score }}</span>
                </div>
            </div>
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
export default {
    name: "SReplyScore",
    data() {
        return {
            studentData: {
                studentId: "100001",
                studentName: "xue",
            },
            teacherData: {
                teacherName: "陈良育",
            },
            projectData: {
                projectName: "ChatGPT背后的人工智能大模型的技术影响及应用展望",
                projectDescribe: "..........",
            },
            paperResult: {
                score: "通过",
                review1: {
                    score: "90",
                    comment: "这篇文章很不错",
                },
                review2: {
                    score: "90",
                    comment: "这篇文章确实很不错",
                },
                review3: {
                    score: "90",
                    comment: "这篇文章真的很不错",
                },
            },
            studentName: this.$store.state.userInfo.userName,
            projectName: "",
            className: "",
            status_code: 12,
        };
    },
    created() {
        this.getStudentData();
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
    },
};
</script>

<style scoped>
</style>
