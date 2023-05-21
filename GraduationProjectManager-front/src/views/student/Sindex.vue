<template>
    <a-layout>
        <SMenu />
        <a-layout :style="{ marginLeft: '300px' }">
            <Header />
            <a-layout-content
                :style="{ margin: '24px 20px 0', overflow: 'initial' }"
            >
                <router-view v-if="$route.matched[1]" />
            </a-layout-content>
        </a-layout>
    </a-layout>
</template>

<script>
import SMenu from "./SMenu";
import Header from "../common/Header";
export default {
    name: "Sindex",
    components: { SMenu, Header }
};
</script>

<style scoped>
main {
    height: calc(100vh - 206px);
    background-color: #fff;
}
</style>
