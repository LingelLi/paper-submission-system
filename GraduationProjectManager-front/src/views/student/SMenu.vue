<template>
    <a-layout-sider
        :style="{
            overflow: 'auto',
            height: '100vh',
            position: 'fixed',
            left: 0
        }"
        width="{300px}"
    >
        <div
            class="logo"
            :style="{ textAlign: 'center' }"
            style="width: 300px"
        >
            <h1 style="color: white;margin-top: 30px;">华东师范大学研究生毕业论文管理系统</h1>
        </div>
        <a-menu
            theme="dark"
            style="width: 300px"
            :default-selected-keys="['1']"
            :open-keys.sync="openKeys"
            mode="inline"
            @click="handleClick"
        >
            <a-menu-item
                key="sub_home"
                @titleClick="titleClick"
                @click="menuClick('/sindex/')"
            >
                <a-icon type="home" />首页
            </a-menu-item>
            <a-menu-item key="sub_info" @click="menuClick('/sindex/sinfo/')">
                <a-icon type="user"></a-icon>个人信息
            </a-menu-item>
            <!-- <a-menu-item
                key="sub_choose"
                @titleClick="titleClick"
                @click="menuClick('/sindex/schoose')"
            >
                <a-icon type="edit" />选择课题
            </a-menu-item> -->
            <a-sub-menu
                key="sub_process"
                @titleClick="titleClick"
            >
                <span slot="title">
                    <a-icon type="appstore" /><span>流程管理</span>
                </span>
                <a-menu-item
                    key="sub_preexamination"
                    @click="menuClick('/sindex/spreexamination')"
                >
                    论文预审
                </a-menu-item>
                <a-menu-item
                    key="sub_preliminaryassessment"
                    @click="menuClick('/sindex/spreliminaryassessment')"
                >
                    论文初评
                </a-menu-item>
                <a-menu-item
                    key="sub_review"
                    @click="menuClick('/sindex/sreview')"
                >
                    论文复评
                </a-menu-item>
                <a-menu-item
                    key="sub_defenseapplication"
                    @click="menuClick('/sindex/sdefenseapplication')"
                >
                    答辩申请
                </a-menu-item>
                
            </a-sub-menu>
            <a-menu-item key="sub_grade" @click="menuClick('/sindex/sgrade')">
                <a-icon type="appstore"></a-icon>
                我的成绩
            </a-menu-item>
            <a-menu-item key="sub_degree" @click="menuClick('/sindex/sdegree')">
                <a-icon type="book"></a-icon>
                学位申请
            </a-menu-item>

            <a-menu-item
                key="sub_library"
                @titleClick="titleClick"
                @click="menuClick('/sindex/slibrary')"
            >
                <a-icon type="home" />历史文库
            </a-menu-item>
            <a-menu-item
                key="sub_download"
                @titleClick="titleClick"
                @click="menuClick('/sindex/sdownload')"
            >
                <a-icon type="home" />资料文件下载
            </a-menu-item>
        </a-menu>
    </a-layout-sider>
</template>

<script>
export default {
    name: "StudentMenu",
    data() {
        return {
            current: ["mail"],
            openKeys: ["sub1"]
        };
    },
    watch: {
        openKeys(val) {
            // console.log("openKeys", val);
        }
    },
    methods: {
        handleClick(e) {
            // console.log("click", e);
        },
        titleClick(e) {
            // console.log("titleClick", e);
        },
        menuClick(route) {
            this.$router.push({ path: route });
        }
    }
};
</script>

<style scoped></style>
