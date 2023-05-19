package com.example.commom.src.main.java.com.example.commom.Entity.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

// Auto generate by mybatis-flex, do not modify it.
public class Tables {


    public static final AttendancerecordTableDef ATTENDANCERECORD = new AttendancerecordTableDef("attendacneRecord");

    public static class AttendancerecordTableDef extends TableDef {

        public QueryColumn ID = new QueryColumn(this, "id");
        public QueryColumn USER_ID = new QueryColumn(this, "user_id");
        public QueryColumn ATTENDANCE_RULE_ID = new QueryColumn(this, "attendance_rule_id");
        public QueryColumn PUNCH_IN_TIME = new QueryColumn(this, "punch_in_time");
        public QueryColumn PUNCH_OUT_TIME = new QueryColumn(this, "punch_out_time");

        public QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, USER_ID, ATTENDANCE_RULE_ID, PUNCH_IN_TIME, PUNCH_OUT_TIME};
        public QueryColumn[] ALL_COLUMNS = new QueryColumn[]{ID, USER_ID, ATTENDANCE_RULE_ID, PUNCH_IN_TIME, PUNCH_OUT_TIME};


        public AttendancerecordTableDef(String tableName) {
            super(tableName);
        }
    }


    public static final AttendanceruleTableDef ATTENDANCERULE = new AttendanceruleTableDef("attendanceRule");

    public static class AttendanceruleTableDef extends TableDef {

        public QueryColumn ID = new QueryColumn(this, "id");
        public QueryColumn NAME = new QueryColumn(this, "name");
        public QueryColumn START_TIME = new QueryColumn(this, "start_time");
        public QueryColumn END_TIME = new QueryColumn(this, "end_time");
        public QueryColumn BELONG = new QueryColumn(this, "belong");
        public QueryColumn SHIFT_DURATION = new QueryColumn(this, "shift_duration");

        public QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, NAME, START_TIME, END_TIME, BELONG, SHIFT_DURATION};
        public QueryColumn[] ALL_COLUMNS = new QueryColumn[]{ID, NAME, START_TIME, END_TIME, BELONG, SHIFT_DURATION};


        public AttendanceruleTableDef(String tableName) {
            super(tableName);
        }
    }


    public static final UsersTableDef USERS = new UsersTableDef("users");

    public static class UsersTableDef extends TableDef {

        public QueryColumn ID = new QueryColumn(this, "id");
        public QueryColumn NAME = new QueryColumn(this, "name");
        public QueryColumn EMAIL = new QueryColumn(this, "email");
        public QueryColumn PASSWORD = new QueryColumn(this, "password");
        public QueryColumn POSITION = new QueryColumn(this, "position");
        public QueryColumn CONTACT = new QueryColumn(this, "contact");

        public QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, NAME, EMAIL, PASSWORD, POSITION, CONTACT};
        public QueryColumn[] ALL_COLUMNS = new QueryColumn[]{ID, NAME, EMAIL, PASSWORD, POSITION, CONTACT};


        public UsersTableDef(String tableName) {
            super(tableName);
        }
    }


    public static final UserRolesTableDef USER_ROLES = new UserRolesTableDef("user_roles");

    public static class UserRolesTableDef extends TableDef {

        public QueryColumn USER_ID = new QueryColumn(this, "user_id");
        public QueryColumn ROLE_ID = new QueryColumn(this, "role_id");

        public QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{USER_ID, ROLE_ID};
        public QueryColumn[] ALL_COLUMNS = new QueryColumn[]{USER_ID, ROLE_ID};


        public UserRolesTableDef(String tableName) {
            super(tableName);
        }
    }


    public static final RolesTableDef ROLES = new RolesTableDef("roles");

    public static class RolesTableDef extends TableDef {

        public QueryColumn ID = new QueryColumn(this, "id");
        public QueryColumn ROLE = new QueryColumn(this, "role");

        public QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, ROLE};
        public QueryColumn[] ALL_COLUMNS = new QueryColumn[]{ID, ROLE};


        public RolesTableDef(String tableName) {
            super(tableName);
        }
    }
}
