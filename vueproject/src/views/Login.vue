<template>
    <div class="wrapperLogin">
        <div style="margin: 200px auto; background-color: #fff; width: 350px; height: 300px; padding: 20px; border-radius: 10px">
            <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>用 户 登 录</b></div>
            <el-form :model="user" :rules="rules" ref="userForm">
                <el-form-item prop="username">
                    <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.username"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
                </el-form-item>
                <el-form-item style="margin: 10px 0; text-align: right">
                    <el-button type="primary" size="small"  autocomplete="off" @click="login">登录</el-button>
                    <el-button type="warning" size="small"  autocomplete="off" @click="register">注册</el-button>
                    <el-button type="info" size="small"  autocomplete="off" @click="visitor">访客</el-button>
                    <el-button type="info" size="small"  autocomplete="off" @click="$router.push('/admin')">管理员登录</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
export default {
    name: "Login",
    data() {
        return {
            visitorMsg:{//访客
                username: "default",
                password: "default"
            },
            user: {},
            rules: {
                username: [
                    { required: true, message: '请输入账号', trigger: 'blur' },
                    { min: 2, max: 10, message: '长度在 2 到 5 个字符', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
                ],
            }
        }
    },
    methods: {
        login() {
            this.$refs['userForm'].validate((valid) => {
                if (valid) {  // 表单校验合法
                    this.request.post("http://localhost:9090/user/login", this.user).then(res => {
                        if(res.code === '200') {
                            localStorage.setItem("user", JSON.stringify(res.data))  // 存储用户信息到浏览器
                            this.$router.push("/")  // 切换路由到主页
                            this.$message.success("登录成功")
                        } else {
                            this.$message.error(res.msg)
                        }
                    })
                }
            });
        },
        register(){
            this.$router.push('/register')
        },
        visitor(){
            this.request.post("http://localhost:9090/user/login", this.visitorMsg).then(res => {
                if(res.code === '200') {
                    localStorage.setItem("user", JSON.stringify(res.data))  // 存储用户信息到浏览器
                    this.$router.push("/")  // 切换路由到主页
                    this.$message.success("欢迎")
                } else {
                    this.$message.error(res.msg)
                }
            })
        }
    }
}
</script>

<style>
.wrapperLogin {
    height: 100vh;
    background-image: linear-gradient(to bottom right, #FC466B , #3F5EFB);
    overflow: hidden;
}
</style>
