<template>
  <div class="container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>User Login</span>
      </div>
      <el-form ref="loginForm" :model="loginForm" label-width="100px">
        <el-form-item label="Email" prop="email">
          <el-input v-model="loginForm.email"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="password">
          <el-input type="password" v-model="loginForm.password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('loginForm')">Login</el-button>
          <el-button type="success" @click="navigateToRegister">Register</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "login",
  data() {
    return {
      loginForm: {
        email: "",
        password: ""
      }
    };
  },
  methods: {
    async submitForm() {
      try {
        const response = await axios.post("http://localhost:10000/api/user/signIn", this.loginForm);
        console.log(response)
        if (response.data.code === 200) {
          this.$message.success("welcome "+ this.loginForm.email);
          localStorage.setItem("token",response.data.data.token);
          this.$router.push(response.data.data.url);
        }else if (response.data.code === 500){
          this.$message.error("login fail");
        }
      } catch (error) {
        console.error(error);
      }
    },
    navigateToRegister() {
      this.$router.push("/register");
    }
  }
};
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f7fa;
}

.box-card {
  width: 400px;
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
</style>
