<template>
    <div>
        <div v-if="status_code == 14" style="margin-left: 85px;margin-top: 30px; font-size: larger; color: green">学位申请审核中</div>
        <div v-if="status_code == 15" style="margin-left: 85px;margin-top: 30px; font-size: larger; color: green">学位申请通过</div>
        <div v-if="status_code == 12">
        <h2 style="margin-left: 40%; font-size:30px">研究生学位申请表</h2>
        <a-card style="margin-top: 24px" :bordered="false" title="毕业信息" v-if="page==0">
            
            <a-form :model="studentData" :label-col="labelCol" :wrapper-col="wrapperCol">

                <a-form-item label="学号">
                    <a-input :value="studentData.studentId" disabled></a-input>
                </a-form-item>
                <a-form-item label="姓名">
                    <a-input :value="studentData.studentName" disabled></a-input>
                </a-form-item>
                <a-form-item label="毕业年月">
                    <a-date-picker :value="graduateData.graduate_time" format="YYYY-MM" @change="graduateTimeChange"></a-date-picker>
                </a-form-item>
                <a-form-item label="毕业后去向">
                    <a-select :value="graduateData.graduate_to">
                        <a-select-option value="0">就业</a-select-option>
                        <a-select-option value="1">升学</a-select-option>
                        <a-select-option value="2">未定</a-select-option>
                    </a-select>
                </a-form-item>
                <a-form-item label="去向省市">
                    <a-input :value="graduateData.graduate_to_loc"></a-input>
                </a-form-item>
                <a-form-item label="工作单位性质">
                    <a-select :value="graduateData.factory_nature">
                        <a-select-option value="0">国家行政企业</a-select-option>
                        <a-select-option value="1">公司合作企业</a-select-option>
                        <a-select-option value="2">中外合资企业</a-select-option>
                        <a-select-option value="3">社会组织机构</a-select-option>
                        <a-select-option value="4">国际组织机构</a-select-option>
                        <a-select-option value="5">外资企业</a-select-option>
                        <a-select-option value="6">私营企业</a-select-option>
                        <a-select-option value="7">集体企业</a-select-option>
                        <a-select-option value="8">国防军事企业</a-select-option>
                    </a-select>
                </a-form-item>
                <a-form-item label="工作性质">
                    <a-select :value="graduateData.work_nature">
                        <a-select-option value="0">全职</a-select-option>
                        <a-select-option value="1">兼职</a-select-option>
                    </a-select>
                </a-form-item>
                <a-form-item label="入学前学历">
                    <a-select :value="graduateData.before_education">
                        <a-select-option value="0">本科毕业生</a-select-option>
                        <a-select-option value="1">其他</a-select-option>
                    </a-select>
                </a-form-item>
                <a-form-item label="获学士学位年月">
                    <a-date-picker :value="graduateData.bachelor_time" format="YYYY-MM" @change="bachelorTimeChange"></a-date-picker>
                </a-form-item>
                <a-form-item label="学士学位授予单位">
                    <a-input :value="graduateData.bachelor_school"></a-input>
                </a-form-item>
                <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
                    <a-button type="primary" @click="nextPage">下一页</a-button>
                </a-form-item>
            </a-form>
        </a-card>


        <a-card style="margin-top: 24px" :bordered="false" title="研究生生涯基本情况" v-if="page==1">
            <a-form :model="studentData" :label-col="labelCol" :wrapper-col="wrapperCol">
                
                <a-form-item label="硕士期间奖惩情况">
                    <a-textarea :value="graduateData.reward_punish" :showCount="true" :autosize="{minRows:5}"></a-textarea>
                </a-form-item>
                <a-form-item label="学位申请理由">
                    <a-textarea :value="graduateData.apply_reason" :showCount="true" :autosize="{minRows:5}"></a-textarea>
                </a-form-item>
                <a-form-item label="科研或教育实习情况">
                    <a-textarea :value="graduateData.research" :showCount="true" :autosize="{minRows:5}"></a-textarea>
                </a-form-item>
                <a-form-item label="社会实践情况">
                    <a-textarea :value="graduateData.experience" :showCount="true" :autosize="{minRows:5}"></a-textarea>
                </a-form-item>

                <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
                    <a-button type="primary" @click="lastPage" style="margin-right: 20px;">上一页</a-button>
                    <a-button type="primary" @click="nextPage">下一页</a-button>
                </a-form-item>
            </a-form>
        </a-card>

        <a-card style="margin-top: 24px" :bordered="false" title="研究生学位论文信息" v-if="page==2">
            <a-form :model="studentData" :label-col="labelCol" :wrapper-col="wrapperCol">

                <a-form-item label="论文题目">
                    <a-input :value="graduateData.paper_title"></a-input>
                </a-form-item>
                <a-form-item label="论文类型">
                    <a-select :value="graduateData.paper_type">
                        <a-select-option value="0">基础研究</a-select-option>
                        <a-select-option value="1">应用研究</a-select-option>
                        <a-select-option value="2">综合研究</a-select-option>
                    </a-select>
                </a-form-item>
                <a-form-item label="论文选题来源">
                    <a-input :value="graduateData.paper_from"></a-input>
                </a-form-item>
                <a-form-item label="论文内容简介">
                    <a-textarea :value="graduateData.paper_describe" :showCount="true" :autosize="{minRows:5}"></a-textarea>
                </a-form-item>
            
                <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
                    <a-button type="primary" @click="lastPage" style="margin-right: 20px;">上一页</a-button>
                    <a-button type="primary" @click="nextPage">下一页</a-button>
                </a-form-item>
            </a-form>
        </a-card>
            
        <a-card style="margin-top: 24px" :bordered="false" title="院系和导师意见" v-if="page==3">
            <a-form :model="studentData" :label-col="labelCol" :wrapper-col="wrapperCol">
                
                <a-form-item label="导师意见">
                    <a-textarea :value="graduateData.teacher_opinion" :showCount="true" :autosize="{minRows:5}" disabled></a-textarea>
                </a-form-item>
                <a-form-item label="院系意见">
                    <a-textarea :value="graduateData.college_opinion" :showCount="true" :autosize="{minRows:5}" disabled></a-textarea>
                </a-form-item>
                <!-- <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
                    <a-button type="primary" @click="nextPage">下一页</a-button>
                </a-form-item> -->
            </a-form>
        </a-card>


        <a-card style="margin-top: 24px" :bordered="false" title="简历上传" v-if="page==3">
            <a-form :model="studentData" :label-col="labelCol" :wrapper-col="wrapperCol">
                
                <a-form-item label="简历">
                    <a-upload-dragger
                v-model:fileList="fileList"
                name="file"
                :multiple="false"
                action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                accept=".doc,.docx,.pdf"
            >
            <p class="ant-upload-drag-icon">
                <inbox-outlined></inbox-outlined>
            </p>
            <p class="ant-upload-text">
                单击上传个人简历
            </p>
            <p class="ant-upload-hint">
                只支持上传doc、docx或pdf格式的文件
            </p>
            </a-upload-dragger>
            </a-form-item>

                <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
                    <a-button type="primary" @click="lastPage" style="margin-right: 20px;">上一页</a-button>
                    <a-button type="primary" @click="submit">提交</a-button>
                </a-form-item>
            </a-form>
        </a-card>
    </div>
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
    name: "SReplyScore",
    data() {
        return {
            studentData: {
                studentId: "100001",
                studentName: "xue",
            },
            graduateData: {
                graduate_time: "2023-06",
                graduate_to: "0",
                graduate_to_loc: "上海市",
                factory_nature: "0",
                work_nature: "0",
                before_education: "0",
                bachelor_time: "2018-06",
                bachelor_school: "华东师范大学",
                reward_punish: "",
                apply_reason: "",
                research: "",
                experience: "",
                paper_title: "",
                paper_type: "",
                paper_from: "",
                paper_describe: "",
                teacher_opinion: "",
                college_opinion: "",
            },
            teacherData: {
                teacherName: "陈良育",
            },
            studentName: this.$store.state.userInfo.userName,
            labelCol: {
                span: 4,
            },
            wrapperCol: {
                span: 14,
            },
            page: 0,
            fileList: [],
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
        graduateTimeChange(val) {
            this.graduateData.graduate_time = moment(val).format("YYYY-MM")
        },
        bachelorTimeChange(val) {
            this.graduateData.bachelor_time = moment(val).format("YYYY-MM")
        },
        nextPage() {
            this.page += 1
        },
        lastPage() {
            this.page -= 1
        },
        submit() {
            this.status_code = 14
            //TODO
        }
    },
};
</script>

<style scoped>
</style>
