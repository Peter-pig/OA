<template>
  <div>
    <el-button @click="dialogVisible = true">个人信息</el-button>
    <el-button @click="pwdDialogVisible = true">修改密码</el-button>
    <el-button @click="openDialog">打卡</el-button>
    <el-dialog
        :visible.sync="dialogVisible2"
        title="打卡时间">
      <el-date-picker
          v-model="time"
          type="datetime"
          placeholder="选择日期时间">
      </el-date-picker>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="punchCard">打卡</el-button>
      </span>
    </el-dialog>
    <el-dialog title="个人信息" :visible.sync="dialogVisible" width="50%">
      <el-form :model="userInfo" label-width="120px">
        <el-form-item label="Name">
          <el-input v-model="userInfo.name"></el-input>
        </el-form-item>
        <el-form-item label="Email">
          <el-input v-model="userInfo.email"></el-input>
        </el-form-item>
        <el-form-item label="Position">
          <el-input v-model="userInfo.position"></el-input>
        </el-form-item>
        <el-form-item label="Phone Number">
          <el-input v-model="userInfo.contact"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updateUserInfo()">Update</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog title="修改密码" :visible.sync="pwdDialogVisible" width="50%">
      <el-form :model="pwdInfo" :rules="rules" ref="pwdForm" label-width="120px">
        <el-form-item label="原密码" prop="oldPwd">
          <el-input v-model="pwdInfo.oldPwd" type="password"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPwd">
          <el-input v-model="pwdInfo.newPwd" type="password"></el-input>
        </el-form-item>
        <el-form-item label="确认新密码" prop="confirmPwd">
          <el-input v-model="pwdInfo.confirmPwd" type="password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updatePassword()">更改密码</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "employee",
  data() {
    return {
      dialogVisible: false,
      pwdDialogVisible: false,
      pwdInfo: {
        oldPwd: "",
        newPwd: "",
        confirmPwd: ""
      },
      dialogVisible2: false,
      time: '',
      userInfo: {
        name: "",
        email: "",
        position: "",
        contact: ""
      }, rules: {
        newPwd: [
          {required: true, message: '请填写新密码', trigger: 'blur'},
          {min: 6, message: '新密码长度不能小于6个字符', trigger: 'blur'}
        ],
        confirmPwd: [
          {validator: this.validateConfirmPwd, trigger: 'blur'}
        ]
      }
    };
  },
  created() {
    this.getUserInfo();
  },
  methods: {
    openDialog() {
      this.dialogVisible2 = true;
    },
    async punchCard() {
      // 打卡逻辑
      const token = localStorage.getItem("token");

      try {
        const response = await axios.post("http://localhost:10000/api/attendance/punchIn", {
            punchInTime:this.time
        }, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        if (response.status === 200) {
          // 处理成功的打卡逻辑
          console.log("打卡成功");
          this.dialogVisible = false;
        } else {
          // 处理打卡失败的逻辑
          console.log("打卡失败");
        }
      } catch (error) {
        // 处理网络请求失败的逻辑
        console.error(error);
      }
    },
    validateConfirmPwd(rule, value, callback) {
      if (value === this.pwdInfo.oldPwd) {
        callback(new Error('新密码不能与原密码相同!'));
      } else if (value !== this.pwdInfo.newPwd) {
        callback(new Error('两次输入的密码不一致!'));
      } else {
        callback();
      }
    },

    async updatePassword() {
      // validate new password and confirm password
      this.$refs.pwdForm.validate(async (valid) => {
        if (!valid) return;

        // if valid, send the password update request to the API
        try {
          const response = await axios.put(`http://localhost:10000/api/user/changePassword?email=${this.userInfo.email}`, this.pwdInfo);
          if (response.data.code === 200) {
            this.$message.success("Password update successful.");
            this.pwdDialogVisible = false;
          }else if (response.data.code === 500){
            this.$message.error("Password update fail.");
          }
        } catch (error) {
          console.error(error);
        }
      });
    },
    async getUserInfo() {
      // get user info from API
      try {
        const token = localStorage.getItem("token")
        const response = await axios.get(`http://localhost:10000/api/user/userInfo`, {
          headers: {
            "Authorization": `Bearer ${token}`
          }
        });
        if (response.status === 200) {
          this.userInfo = response.data.data.data;
        }
      } catch (error) {
        console.error(error);
      }
    },
    async updateUserInfo() {
      // update user info through API
      try {
        const response = await axios.put("http://localhost:10000/api/user/userUpdata", this.userInfo);
        if (response.status === 200) {
          this.$message.success("Update successful.");
          this.dialogVisible = false;
        }
      } catch (error) {
        console.error(error);
      }
    }
  }
};
</script>
