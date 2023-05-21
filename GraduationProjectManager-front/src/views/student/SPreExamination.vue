<template>
    <div>
        <a-card style="margin-top: 24px" :bordered="false" title="论文预审">
            <div v-if="status_code == 2" style="margin-left: 85px;margin-bottom: 30px; font-size: larger; color:green">开题报告已提交</div>
            <div v-if="status_code == 3" style="margin-left: 85px;margin-bottom: 30px; font-size: larger; color:green">预审已通过</div>
            <div v-if="status_code == 4" style="margin-left: 85px;margin-bottom: 30px; font-size: larger; color:red">预审未通过</div>
            <a-form :model="studentData" :label-col="labelCol" :wrapper-col="wrapperCol">
                <a-form-item label="学号">
                    <a-input :value="studentData.studentId" disabled></a-input>
                </a-form-item>
                <a-form-item label="论文题目（中文）">
                    <a-input :value="projectData.projectName" :disabled="status_code != 1 && status_code != 4"></a-input>
                </a-form-item>
                <a-form-item label="论文题目（英文）">
                    <a-input :value="projectData.projectEngName" :disabled="status_code != 1 && status_code != 4"></a-input>
                </a-form-item>
                <a-form-item label="论文关键字">
                    <a-input :value="projectData.projectKeyWords" :disabled="status_code != 1 && status_code != 4"></a-input>
                </a-form-item>
                <a-form-item label="论文类型">
                    <a-select :value="projectData.projectType" :disabled="status_code != 1 && status_code != 4">
                        <a-select-option value="0">基础研究</a-select-option>
                        <a-select-option value="1">应用研究</a-select-option>
                        <a-select-option value="2">综合研究</a-select-option>
                    </a-select>
                </a-form-item>
                <a-form-item label="论文选题来源">
                    <a-input :value="projectData.projectFrom" :disabled="status_code != 1 && status_code != 4"></a-input>
                </a-form-item>
                <a-form-item label="创新点和主要内容">
                    <a-textarea :value="projectData.projectDescribe" :showCount="true" :autosize="{minRows:5}" :disabled="status_code != 1 && status_code != 4"></a-textarea>
                </a-form-item>
                <a-form-item label="开题报告上传">
                    <a-upload-dragger
                v-model:fileList="fileList"
                name="file"
                :multiple="false"
                action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                @change="handleChange"
                @remove="handleRemove"
                accept=".doc,.docx,.pdf"
                :disabled="status_code != 1 && status_code != 4"
            >
            <p class="ant-upload-drag-icon">
                <inbox-outlined></inbox-outlined>
            </p>
            <p class="ant-upload-text">
                单击上传开题报告
            </p>
            <p class="ant-upload-hint">
                只支持上传doc、docx或pdf格式的文件
            </p>
            </a-upload-dragger>
                </a-form-item>
                <a-form-item  :wrapper-col="{ span: 14, offset: 4 }">
                    <a-button type="primary" @click="submit" :disabled="status_code==2 || status_code==3">提交</a-button>
                </a-form-item>
            </a-form>
            
            <a-form :model="studentData" :label-col="labelCol" :wrapper-col="wrapperCol" v-if="status_code == 4">
                <a-form-item label="导师意见">
                    <a-textarea :value="result.teacher_comment" :autosize="{minRows:5}"></a-textarea>
                </a-form-item>
                <a-form-item label="院系意见">
                    <a-textarea :value="result.college_comment" :autosize="{minRows:5}"></a-textarea>
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
import { message } from 'ant-design-vue';
export default {
    name: "SPreliminaryAssessment",
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
                projectDescribe: "................."
            },
            result: {
                teacher_comment: "",
                college_comment: "",
            },
            labelCol: {
                span: 4,
            },
            wrapperCol: {
                span: 14,
            },
            fileList: [],
            status_code: 1,
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
                        this.projectData = response.data.data
                    }
                );
            } else {
                
            }
        },
        handleChange(file, fileList, event) {
            //这里不起作用
            if(file == undefined){
                message.error("上传失败")
            }
            if(file.status == "removed"){
                message.warning("移除文件成功")
            }
            else if(file.status == "error"){
                message.error("上传失败")
            }
            else if(file.status == "done"){
                message.success("上传成功")
            }
        },
        handleRemove(file) {
            
        },
        submit() {
            this.status_code = 2
            //TODO
        }
    },
};
</script>

<style scoped>
</style>
