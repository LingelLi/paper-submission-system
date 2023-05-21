<template>
    <div id="app">
        <router-view></router-view>
    </div>
</template>

<script>
export default {
    name: "App",
    data() {
        return {};
    },

    //App组件创建时
    created() {
        // 页面刷新后将sessionStorage的数据取出来存入vuex
        // 页面刷新时将vuex里的信息保存到sessionStorage里
        window.addEventListener("unload", () => {
            sessionStorage.setItem("store", JSON.stringify(this.$store.state));
        });
    }
};
</script>

<style scoped>
h1 {
    color: red;
}
#app{
 height: 100%;
 width: 100%;
}
</style>
