<template>
    <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
             background-color="rgb(48,65,86)"
             text-color="#fff"
             active-text-color="#ffd04b"
             :collapse-transition="false"
             :collapse="isCollapse"
             router
    >
        <!-- router的作用：加上之后就可以在下面index中设置和使用路由       -->
        <!--在属性前加上冒号 : 或使用 v-bind 表达式时，Vue.js 会将属性的值解析为 JavaScript 表达式，并将表达式的结果绑定到该属性上。可以实现动态绑定，当数据或计算属性发生变化时，相关的属性也会相应地更新。
        如果不加冒号，则属性值会被视为字符串直接赋值给该属性，不会进行解析或绑定。-->
        <div style="height: 60px; line-height: 60px; text-align: center">
            <img src="../assets/logo.png" alt="" style="width: 20px;position: relative;top: 5px;margin-right: 5px">
            <b style="color: #cccccc" v-show="logoTextShow">
                后台管理系统
            </b>
        </div>
        <el-menu-item index="/home">
<!--            当被点击后路由就会被重定向为.../home-->
            <template slot="title">
                <i class="el-icon-house"></i>
                <span slot="title">主页</span>
            </template>
        </el-menu-item>
        <el-submenu index="2">
            <template slot="title">
                <i class="el-icon-menu"></i>
                <span slot="title">系统管理</span>
            </template>
            <el-menu-item index="/user">
                <i class="el-icon-s-custom"></i>
                <span slot="title">用户管理</span>
            </el-menu-item>
            <el-menu-item index="/file">
                <i class="el-icon-document"></i>
                <span slot="title">文件管理</span>
            </el-menu-item>
        </el-submenu>
    </el-menu>
</template>

<script>
export default {
    name: "Aside",//定义组件名称
    props:{//定义组件属性
        isCollapse: Boolean,
        logoTextShow: Boolean
    }
}
</script>

<style scoped>

</style>