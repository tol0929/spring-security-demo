package com.example.springsecuritydemo.mbgenerate.crud;

import com.example.springsecuritydemo.mbgenerate.entity.LoginUser;
import com.example.springsecuritydemo.mbgenerate.entity.LoginUserRolesEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CustomLoginUserMapper extends LoginUserMapper {
    @Select("SELECT * FROM login_user;")
    List<LoginUser> selectAll();

    @Select("/mbgenerate/crud/CustomLoginUserMapper/selectAll.sql")
    List<LoginUser> selectAllBySqlFile();

    @Select("/mbgenerate/crud/CustomLoginUserMapper/selectLoginUserRolesEntity.sql")
    Optional<LoginUserRolesEntity> selectLoginUserRolesEntity(@Param("userId") int userId);

    /**
     * common/db/mapper/UserMapper.java
     * src/main/resources/db/mapper/UserMapper/XXX.sql
     */

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "LoginUserResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "roles_*", property = "")
    })
    List<LoginUser> selectLoginUserRolesEntity2(SelectStatementProvider selectStatement);
}