<template>
    <div>
        <a-modal
            @ok="save"
            @cancel="close"
            v-model:visible="visible"
            title="Basic Modal"
        >
            <a-form-model
                :model="form"
                :label-col="{ span: 5 }"
                :wrapper-col="{ span: 12 }"
                ref="formRef"
                :rules="rules"
            >
                <!-- a-form-model-item是表单里每一个表单项，如用户账号，用户名等 -->
                <a-form-model-item
                    label="选题名"
                    prop="projectName"
                >
                    <!-- 每一项里边的内容，prop绑定的规则名，下见data里的rules对象 -->
                    <a-input
                        v-model="form.projectName"
                        placeholder="请输入选题名称"
                    />
                </a-form-model-item>
                <a-form-model-item
                    label="选题详细信息"
                    prop="projectDescribe"
                >
                    <a-input
                        v-model="form.projectDescribe"
                        placeholder="请输入选题详细信息"
                    />
                </a-form-model-item>

                <a-form-model-item
                    label="选题来源"
                    prop="projectFromId"
                >
                    <a-select
                        v-model="form.projectFromId"
                        placeholder="选题来源"
                    >
                        <a-select-option value="1">自定选题</a-select-option>
                        <a-select-option value="2">社会实践来源</a-select-option>
                    </a-select>
                </a-form-model-item>
            </a-form-model>
        </a-modal>
    </div>
</template>

<script>
import { message } from "ant-design-vue";
import { requestAddProject, requestUpdateProject } from "@/api/project.js"
export default {
    name: "AProjectEditDialog",
    emit: ["fetch-data"],
    data() {
        return {
            // 弹窗显示开关
            visible: false,
            // 弹窗左上角的标题
            title: "",
            // 是否为编辑按钮
            isEdit: false,
            // 表单对象,用来获取整个表单组件
            formRef: null,
            // 表单内的值所绑定的对象，数据都在这里边
            form: {},
            // 表单校验规则
            rules: {
                // userAccount规则名，数组里的每个对象是有几条校验规则，每个都回跑一遍，
                projectName: [
                    // required是否为必填项，非必填时可以不写
                    // message是不满足条件时的提示信息
                    // trigger是触发方式:blur失焦
                    { required: true, message: "请输入选题名", trigger: "blur" },
                    { min: 5, message: "长度不少于 6 个字符", trigger: "blur" }
                    //validator自定义校验规则函数，一般提示需要必填就足够用了，不开这个
                    // { validator: this.validatePassword }
                ],
                projectDescribe: [
                    { required: true, message: "请输入选题详细描述", trigger: "blur" },
                ],
                projectFromId: [
                    { required: true, message: "请选择选题来源", trigger: "blur" },

                ]
            }
        };
    },
    methods: {
        /**
         * @description 弹窗展示
         * @author Cui Ruichen
         * @date 2022-04-29
         */
        showEdit(record) {
            // record: 该行的数据，如果是编辑按钮(行外添加record为null)，就把行内数据传给表单对象，用来回显
            if (record) {
                this.title = "编辑";
                // 把编辑标识设置为true
                this.isEdit = true;
                // 对record数据进行深拷贝，否则this.form只会拷贝record的地址，修改this.form会影响表格内的源数据
                this.form = JSON.parse(JSON.stringify(record));
                console.log(this.form.userRoles);
                // 这里 +"" 将值从Number转为String，否则select单选框组件不会回显
                this.form.userRoles = this.form.userRoles + "";
                this.form.userSex = this.form.userSex + "";
            } else {
                this.title = "添加";
            }
            this.visible = true;
        },

        /**
         * @description 取消事件
         * @author Cui Ruichen
         * @date 2022-04-29
         */
        close() {
            // 清空规则校验
            this.$refs.formRef.resetFields();
            // 调用父组件的查询数据方法
            this.$emit("fetch-data");
            // 关闭弹窗时要清空表单对象，否则会有残留的数据
            this.form = {};
            this.visible = false;
        },

        /**
         * @description 确认事件
         * @author Cui Ruichen
         * @date 2022-04-29
         */
        save() {
            this.$refs.formRef.validate(valid => {
                // 如果所有项都通过了校验
                if (valid) {
                    //console.log("err", err, values);
                    // 如果为编辑
                    if (this.isEdit) {
                        requestUpdateProject(this.form).then((response) => {
                            if (response.data.code == 1) {
                                message.success("编辑成功");
                            } else {
                                message.error("编辑失败");
                            }
                        })
                    }
                    // 如果为添加
                    else {
                        requestAddProject(this.form).then((response) => {
                            if (response.data.code == 1) {
                                message.success("添加成功");
                            } else {
                                message.error("添加失败");
                            }
                        })
                    }
                    this.close();
                }
                // 如果校验不通过
                else {
                    return false;
                }
            });
        }
    }
};
</script>

<style>
</style>
