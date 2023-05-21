<template>
    <div class="bg">
        <div v-if = "page == 0 && status_code == 0">
        <h2 style="margin: 30px 0 40px 40%; font-size: 30px;">个人基本信息确认</h2>
        <a-form :model="studentData" :label-col="labelCol" :wrapper-col="wrapperCol">
            <a-form-item label="学号">
                <a-input :value="studentData.studentId" :disabled="true"></a-input>
            </a-form-item>
            <a-form-item label="姓名">
                <a-input :value="studentData.user_name" :disabled="true"></a-input>
            </a-form-item>
            <a-form-item label="性别">
                <a-radio-group :value="studentData.user_sex" disabled>
                    <a-radio value="0">男</a-radio>
                    <a-radio value="1">女</a-radio>
                </a-radio-group>
            </a-form-item>
            <a-form-item label="国家和地区">
                <a-select :value="studentData.area" disabled>
                    <a-select-option v-for="(item, index) in areas" :value="index.toString()" :key="index">{{ item.name }}</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="民族">
                <a-select :value="studentData.nation" disabled>
                    <a-select-option v-for="(item, index) in nations" :value="index.toString()" :key="index">{{ item.name }}</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="出生日期">
                <a-date-picker :value="studentData.birthday" @change="birthdayChange" disabled></a-date-picker>
            </a-form-item>
            <a-form-item label="身份证件类型">
                <a-select :value="studentData.card_type" disabled>
                    <a-select-option value="0">身份证</a-select-option>
                    <a-select-option value="1">护照</a-select-option>
                    <a-select-option value="2
                    ">港澳通行证</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="身份证件号码">
                <a-input :value="studentData.card_number" disabled></a-input>
            </a-form-item>
            <a-form-item label="院系">
                <a-input :value="studentData.department" disabled></a-input>
            </a-form-item>
            <a-form-item label="申请学位专业">
                <a-input :value="studentData.major" disabled></a-input>
            </a-form-item>
            <a-form-item label="研究方向">
                <a-input :value="studentData.direction" disabled></a-input>
            </a-form-item>
            <a-form-item label="入学年月">
                <a-date-picker :value="studentData.in_time" format="YYYY-MM" disabled @change="inTimeChange"></a-date-picker>
            </a-form-item>
            <a-form-item label="学习年限">
                <a-input :value="studentData.learn_period" disabled></a-input>
            </a-form-item>
            <a-form-item label="录取类别">
                <a-select :value="studentData.admission_category" disabled>
                    <a-select-option value="0">全日制</a-select-option>
                    <a-select-option value="1">非全日制</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
                <a-button type="primary" @click="confirm">确认上述信息</a-button>
            </a-form-item>
        </a-form>

        <a-modal :visible="visible" title="确认信息" @ok="ok" @cancel="cancel">请确保仔细和对上述信息后再确认</a-modal>
        </div>
        <div v-if="page == 1 && status_code == 0">
            <h2 style="margin: 30px 0 40px 40%; font-size: 30px;">学位和论文信息填写</h2>
            <a-form :model="studentData" :label-col="labelCol" :wrapper-col="wrapperCol">
                <a-form-item label="学位证书使用姓名">
                    <a-input :value="studentData.use_name"></a-input>
                </a-form-item>
                <a-form-item label="导师姓名">
                    <a-input :value="teacherData.teacher_name"></a-input>
                </a-form-item>
                <a-form-item label="导师工作单位">
                    <a-input :value="teacherData.teacher_institution"></a-input>
                </a-form-item>
                <a-form-item label="导师从事学科领域">
                    <a-input :value="teacherData.teacher_major"></a-input>
                </a-form-item>
                <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
                    <a-button type="primary" @click="save">保存</a-button>
                </a-form-item>
            </a-form>
        </div>
        <div v-if="status_code >= 1" :wrapper-col="{ span: 14, offset: 4 }" style="margin-top: 30px;font-size: larger;margin-left: 40px;">
            <div>个人信息已确认</div>
        </div>
    </div>
    
</template>

<script>
import {
    requestCurrentStudentData,
    requestStudentByUserId,
} from "@/api/student.js";
import { requestProjectById,requestProjectList } from "@/api/project.js";
import { requestNameByTeacherId } from "@/api/teacher.js";
import moment from 'moment';
import area from "../../../static/area.json";
import nation from "../../../static/nation.json";
import { message } from 'ant-design-vue';
export default {
    name: "SHome",
    data() {
        return {
            page: 0,
            curUserId: "",
            curUserName: "",
            curUserTel: "",
            currentStep: 0,
            studentData: {
                studentId: "100001",
                user_name: "xue",
                user_sex: "1",
                area: "0",
                nation: "0",
                birthday: "2000-05-31",
                card_type: "0",
                card_number: "41070220000531****",
                department: "软件工程学院",
                major: "软件工程",
                direction: "软件科学与技术",
                in_time: "2022-09",
                learn_period: "3",
                admission_category: "0",
                use_name: "",
            },
            teacherData: {
                teacher_name: "",
                teacher_institution: "",
                teacher_major: "",
            },
            areas: area,
            nations: nation,
            projectData: [],
            teacherName: "",
            have_paper: false,
            labelCol: {
                span: 4,
            },
            wrapperCol: {
                span: 14,
            },
            visible: false,
            status_code: 0,
        };
    },
    created() {
        this.curUserId = this.$store.state.userInfo.userId;
        this.curUserName = this.$store.state.userInfo.userName;
        this.curUserTel = this.$store.state.userInfo.userTel;
        //this.initData();
    },
    methods: {
        initData() {
            requestCurrentStudentData()
                .then((response) => {
                    this.studentData = response.data.data;
                    console.log(this.studentData)
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

        birthdayChange(val) {
            this.studentData.birthday = moment(val).format("YYYY-MM-DD")
        },
        inTimeChange(val) {
            this.studentData.in_time = moment(val).format("YYYY-MM")
        },
        confirm() {
            this.visible = true
        },
        ok() {
            this.visible = false
            this.page = 1
            //确认 TODO
        },
        cancel() {
            this.visible = false
        },
        save() {
            message.success("保存成功")
            this.status_code = 1 //传到后端
        },
    },
};
</script>

<style scoped>
    .bg{
        background: white;
    }
</style>