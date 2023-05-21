<template>
    <a-layout>
        <a-layout-header style="padding: 0 80px">
            <h2 style="color: white">
                华东师范大学研究生毕业论文管理系统
            </h2>
        </a-layout-header>
        <a-layout-content style="padding: 0 80px;">
            <div :style="{ padding: '32px 0px', height: '500px' }">
                <div
                    class="login-div"
                    v-bind:style="{ backgroundImage: 'url(' + bg + ')'}"
                >
                    <a-form
                        class="login-form"
                        id="components-form-demo-normal-login"
                        :form="form"
                        @submit="handleSubmit"
                    >
                        <a-form-item style="font-family:微软雅黑;font-size: 25px;text-align: center;font-weight: bolder;">
                            登录
                        </a-form-item>
                        <a-form-item  style="width: 70%; left: 15%;">
                            <a-input
                                v-decorator="[
                                    'account',
                                    {
                                        rules: [
                                            {
                                                required: true,
                                                message: '请输入你的账户!'
                                            }
                                        ]
                                    }
                                ]"
                                placeholder="输入账号"
                            >
                                <a-icon
                                    slot="prefix"
                                    type="user"
                                    style="color: rgba(0,0,0,.25)"
                                />
                            </a-input>
                        </a-form-item>
                        <a-form-item style="width: 70%; left: 15%;">
                            <a-input  
                                v-decorator="[
                                    'password',
                                    {
                                        rules: [
                                            {
                                                required: true,
                                                message: '请输入你的密码!'
                                            }
                                        ]
                                    }
                                ]"
                                type="password"
                                placeholder="输入密码"
                            >
                                <a-icon
                                    slot="prefix"
                                    type="lock"
                                    style="color: rgba(0,0,0,.25)"
                                />
                            </a-input>
                        </a-form-item>
                        <a-form-item style="width: 70%; left: 15%;">
                            <a-checkbox  v-decorator="[
                                    'remember',
                                    {
                                        valuePropName: 'checked',
                                        initialValue: true
                                    }
                                ]">
                                记住我
                            </a-checkbox>
                            <div
                                class="login-form-forgot"
                                @click="forget"
                            >
                                忘记密码
                        </div>
                            <a-button
                            style="width: 70%; left: 15%;margin-top: 30px;"
                                type="primary"
                                html-type="submit"
                                class="login-form-button"
                            >
                                登录
                            </a-button>
                        </a-form-item>
                    </a-form>
                </div>
            </div>
            <div style="margin-top:32px">
                    <a-row type="flex">
                        <a-col
                            :flex="3"
                            :style="{
                                marginRight: '32px',
                                borderRadius: '5px'
                            }"
                        >
                            <a-list
                                bordered
                                :data-source="informData"
                                class="a-list"
                            >
                                <a-list-item
                                    slot="renderItem"
                                    slot-scope="item, index"
                                    @click="informClick(item)"
                                    class="listitem"
                                >
                                    {{ item.informTitle }}
                                </a-list-item>
                                <div slot="header">
                                    <h3>通知公告</h3>
                                </div>
                                <div slot="footer" @click="to_information" class="listitem">
                                    加载更多
                                </div>
                            </a-list>
                        </a-col>
                        <a-col
                            :flex="2"
                            :style="{ borderRadius: '5px' }"
                        >
                            <!--横向比例布局-->
                            <a-list
                                bordered
                                :data-source="downData"
                                class="a-list"
                            >
                                <a-list-item
                                    slot="renderItem"
                                    slot-scope="item, index"
                                    @click="informClick(item)"
                                    class="listitem"
                                >
                                    {{ item.downTitle }}
                                </a-list-item>
                                <div slot="header">
                                    <h3>文件下载</h3>
                                </div>
                                <div slot="footer" @click="to_download()" class="listitem">
                                    加载更多
                                </div>
                            </a-list>
                        </a-col>
                    </a-row>
                </div>
        </a-layout-content>
        <!-- <a-layout-footer :style="{ textAlign: 'center' }">
            <p>
                由
                <a
                    href="https://github.com/Alone-93"
                    title="CX"
                    target="_blank"
                >CX</a>提供技术支持
            </p>
            <p>版权所有 &copy; 2022/3/1.陈旭</p>
        </a-layout-footer> -->
    </a-layout>
</template>

<script>
import Inform from "@/views/common/Inform";

import {
    login,
    requestDownList,
    requestAnnouncementList
} from "@/api/login.js";


export default {
    components: { Inform },
    name: "Login",
    data() {
        return {
            bg: require("@/assets/1.png"),
            downData: [{downTitle: "【学位】学位论文答辩与学位申请工作相关附件", url:"http://www.yjsy.ecnu.edu.cn/8e/62/c3603a429666/page.htm"},{downTitle:"【专业学位培养】专业学位研究生常用表格（2023年3月更新）",url:"http://www.yjsy.ecnu.edu.cn/5b/84/c3603a88964/page.psp"},{downTitle:"华东师范大学研究生结转毕申请表",url:"http://www.yjsy.ecnu.edu.cn/2f/ab/c3603a405419/page.htm"}],
            informData: [{informTitle: "【学位】关于2022-2023学年第二学期学术型学位硕士研究生论文答辩及学位申请工作的通知",url:"http://www.yjsy.ecnu.edu.cn/98/e9/c36271a497897/page.htm"},{informTitle: "【学位】关于2022-2023学年第二学期教育博士专业学位研究生论文答辩及学位申请工作安排的通知",url:"http://www.yjsy.ecnu.edu.cn/98/d5/c36271a497877/page.htm"},{informTitle: "【学位】关于2022-2023学年第二学期硕士专业学位论文答辩与学位申请工作的通知",url:"http://www.yjsy.ecnu.edu.cn/98/d6/c36271a497878/page.htm"},{informTitle: "【学位】关于2022-2023学年第二学期博士研究生论文答辩及学位申请工作安排的通知",url:"http://www.yjsy.ecnu.edu.cn/98/c8/c36271a497864/page.htm"},{informTitle: "【学术学位】关于2022-2023学年春季学期博士生论文预答辩的通知--培养单位版",url:"http://www.yjsy.ecnu.edu.cn/d8/e9/c3596a448745/page.htm"}]
        };
    },

    beforeCreate() {
        this.form = this.$form.createForm(this, { name: "login" });
    },
    created() {
        this.getDownList();
        this.getInformList();
    },
    methods: {
        // 处理登陆函数
        handleSubmit(e) {
            e.preventDefault();
            this.form.validateFields(async (err, values) => {
                //当输入值不为空
                if (!err) {
                    //发起登陆请求
                    const result = await login({
                        account: values["account"],
                        password: values["password"]
                    });
                    if (result.data.code == 1) {
                        const userInfo = result.data.data;
                        //通过commit调用mutaions中的方法将token存入vuex
                        this.$store.commit("set_token", userInfo.satoken);
                        this.$store.commit("change_user_info", userInfo);
                        this.$message.success("登录成功");
                        if (userInfo.userRoles == 1) {
                            //判断用户角色来跳转不同的主页
                            this.$router.push({ path: "/tindex" });
                        } else if (userInfo.userRoles == 2) {
                            this.$router.push({ path: "/sindex" });
                        } else if (userInfo.userRoles == 3) {
                            this.$router.push({ path: "/aindex" });
                        }
                    } else {
                        this.$message.error("登陆失败:" + result.data.message);
                    }
                }
            });
        },

        // // 获取文件下载列表
        // getDownList() {
        //     requestDownList({ pageNum: 0, pageSize: 5 }).then(response => {
        //         this.downData = response.data.data.data;
        //     });
        // },

        // // 获取通知公告列表
        // getInformList() {
        //     requestAnnouncementList({ pageNum: 0, pageSize: 5 }).then(
        //         response => {
        //             this.informData = response.data.data.data;
        //             console.log(this.informData)
        //         }
        //     );
        // },
        informClick(record) {
            window.open(record.url)
        },
        to_download() {
            window.location.href='http://www.yjsy.ecnu.edu.cn/xzzx/list1.htm'
        },
        to_information() {
            window.location.href="http://www.yjsy.ecnu.edu.cn/"
        },
        forget() {
            // this.$message("请联系管理员老师修改密码")
            alert("请联系管理员老师修改密码")
        }
    }
};
</script>

<style scoped>
.login-div {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    padding: 30px;
    border-radius: 5px;
    background: no-repeat;
    background-size: cover;
}

.login-form {
    width: 35%;
    border-radius: 5px;
    padding: 30px 8px 8px 8px;
    background: rgb(255, 255, 255);
    opacity: 0.9
}

#components-form-demo-normal-login .login-form-forgot {
    float: right;
    color: blue;
    cursor: pointer;
}
#components-form-demo-normal-login .login-form-forgot:hover{
    text-decoration: underline;
}

#components-form-demo-normal-login .login-form-button {
    width: 30%;
    display: block;
    margin: 10px 0 0 0;
}

.a-list {
    background-color: white;
    border-style: none;
    border-radius: 5px;
}

.listitem{
    cursor: pointer;
}
.listitem:hover{
    text-decoration: underline;
}
</style>
