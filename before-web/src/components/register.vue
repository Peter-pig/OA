<template>
  <div class="container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>User Registration</span>
      </div>
      <el-form ref="registerForm" :model="registerForm" label-width="120px">
        <el-form-item label="Name" prop="name">
          <el-input v-model="registerForm.name"></el-input>
        </el-form-item>
        <el-form-item label="Email" prop="email">
          <el-input v-model="registerForm.email"></el-input>
        </el-form-item>
        <el-form-item label="Password" prop="password">
          <el-input type="password" v-model="registerForm.password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('registerForm')">Register</el-button>
          <el-button type="info" @click="navigateToLogin">Login</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "register",
  data() {
    return {
      registerForm: {
        name: "",
        email: "",
        password: ""
      }
    };
  },
  methods: {
    async submitForm(formName) {
      try {
        const response = await axios.post("http://localhost:10000/api/user/register", this.registerForm);
        if (response.status === 200) {
          this.$message.success("Registration successful. Please log in.");
          this.$router.push(response.data.data.data.url);
        }
      } catch (error) {
        console.error(error);
      }
    },
    navigateToLogin() {
      this.$router.push("/");
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
