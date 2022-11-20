package com.example.springsecuritydemo.mbgenerate.crud;

import com.example.springsecuritydemo.mbgenerate.entity.LoginUser;
import com.example.springsecuritydemo.mbgenerate.entity.LoginUserAndRoleNameEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface CustomLoginUserMapper extends LoginUserMapper {
    @Select("SELECT * FROM login_user;")
    List<LoginUser> selectAll();

    @Select("/mbgenerate/crud/CustomLoginUserMapper/selectAll.sql")
    List<LoginUser> selectAllBySqlFile();

    /**
     * common/db/mapper/UserMapper.java
     * src/main/resources/db/mapper/UserMapper/XXX.sql
     */

    // TODO role_nameをリストとし、login_user:roles=1:nでマッピングできないか検討
    // TODO login_user.idの取得ができない問題の解決
    @Select("/mbgenerate/crud/CustomLoginUserMapper/selectLoginUserRolesEntity.sql")
    @Results(id = "LoginUserAndRoleNameEntity", value = {
//            @Result(column = "id", property = " id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "role_name", property = "roleName")
    })
    List<LoginUserAndRoleNameEntity> selectLoginUserRolesEntity(@Param("email") String userId);
}