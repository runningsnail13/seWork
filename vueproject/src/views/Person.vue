<template>
  <el-card style="width: 500px; padding: 20px ">
      <el-form label-width="100px">
          <el-form-item label="用户名" size="small">
              <el-input v-model="form.username" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="昵称" size="small">
              <el-input v-model="form.nickname" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" size="small">
              <el-input v-model="form.email" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="电话" size="small">
              <el-input v-model="form.phone" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="地址" size="small">
              <el-input v-model="form.address" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item>
              <el-button type="primary" @click="$router.push('/')" >离 开</el-button>
              <el-button type="primary" @click="save">修 改</el-button>
          </el-form-item>
      </el-form>

  </el-card>
</template>

<script>
import user from "@/views/User.vue";

export default {
    name: "Person",
    data(){
        return {
            form: {},
            user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
        }
    },
    created() {
        this.request.get("http://localhost:9090/user/username/"+ this.user.username).then(res => {
            if(res.code === '200'){
                this.form=res.data;
            }
        })
    },
    methods :{
        save(){//表单提交，修改还是新增取决于form中有无id项
            this.request.post("http://localhost:9090/user",this.form).then(res => {
                if(res) {
                    this.$message.success("操作成功")
                }
                else
                    this.$message.error("操作失败")
            })
        },
    }
}
</script>

<style scoped>

</style>