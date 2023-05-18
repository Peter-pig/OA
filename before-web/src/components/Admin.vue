<template>
  <div class="admin">
    <el-button type="danger" @click="logout">Logout</el-button>

    <el-input
        placeholder="Search by name"
        v-model="searchName"
        @keyup.enter.native="fetchData"
        clearable
        style="width: 300px">
    </el-input>

    <el-table :data="users" style="width: 100%">
      <el-table-column prop="name" label="Name"></el-table-column>
      <el-table-column prop="email" label="Email"></el-table-column>
      <el-table-column prop="position" label="Position"></el-table-column>
      <el-table-column
          fixed="right"
          label="Operations"
          width="120">
        <template slot-scope="scope">
          <el-button
              type="primary"
              icon="el-icon-edit"
              circle
              @click="openEditDialog(scope.$index, scope.row)"
          />
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :visible.sync="editDialogVisible" title="Edit User">
      <el-form :model="currentUser">
        <el-form-item label="Name">
          <el-input v-model="currentUser.name"/>
        </el-form-item>
        <el-form-item label="Email">
          <el-input v-model="currentUser.email"/>
        </el-form-item>
        <el-form-item label="Position">
          <el-input v-model="currentUser.position"/>
        </el-form-item>
        <el-form-item label="Role">
          <el-select v-model="selectedRole" placeholder="Select role" @change="changeOption">
            <el-option
                v-for="role in roles"
                :key="role.id"
                :label="role.role"
                :value="role.id">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="submitEdit">Submit</el-button>
      </span>
    </el-dialog>

    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10, 20, 50]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>

  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Admin',
  data() {
    return {
      editDialogVisible: false,
      currentUser: {},
      users: [],
      total: 0,
      currentPage: 1,
      pageSize: 10,
      selectedRole: '',
      roles: [],
      searchName: ''
    }
  },
  methods: {
    changeOption(){
      console.log(this.selectedRole);
    },
    async logout() {
      this.$router.push("/")
    },
    openEditDialog(index, row) {
      this.selectedRole = row.roleId
      this.fetchRoles()
      this.currentUser = {...row}; // Make a copy of the row data
      this.editDialogVisible = true;
    },
    async fetchRoles() {
      try {
        const response = await axios.get('http://localhost:10000/api/user/roles');

        if (response.status === 200 && response.data) {
          this.roles = response.data.data
          console.log(this.roles)
        }
      } catch (error) {
        console.error(error);
      }
    },
    async submitEdit() {
      try {
        // Here you should send a request to your server to update the user data
        // For example:
        this.currentUser.roleId = this.selectedRole;
        await axios.put(`http://localhost:10000/api/user/${this.currentUser.id}`, this.currentUser);
        this.editDialogVisible = false;
        this.fetchData(); // Refetch the data after updating
      } catch (error) {
        console.error(error);
      }
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.fetchData();
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.fetchData();
    },
    async fetchData() {
      try {
        const response = await axios.post('http://localhost:10000/api/user/', {
          page: this.currentPage,
          size: this.pageSize,
          name: this.searchName

        });
        this.users = response.data.data.data.records; // Assuming the data is here
        this.total = response.data.data.data.totalRow; // Assuming the total count is here
      } catch (error) {
        console.error(error);
      }
    }
  },
  created() {
    this.fetchData();
  }
}
</script>

<style scoped>
.admin {
  padding: 20px;
}
</style>
