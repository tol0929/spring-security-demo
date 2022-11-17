package com.example.springsecuritydemo.mbgenerate.crud;

import static com.example.springsecuritydemo.mbgenerate.crud.LoginUserDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import com.example.springsecuritydemo.mbgenerate.entity.LoginUser;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface LoginUserMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<LoginUser>, CommonUpdateMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, name, email, password);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="LoginUserResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR)
    })
    List<LoginUser> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("LoginUserResult")
    Optional<LoginUser> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, loginUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, loginUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(LoginUser row) {
        return MyBatis3Utils.insert(this::insert, row, loginUser, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(email).toProperty("email")
            .map(password).toProperty("password")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<LoginUser> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, loginUser, c ->
            c.map(id).toProperty("id")
            .map(name).toProperty("name")
            .map(email).toProperty("email")
            .map(password).toProperty("password")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(LoginUser row) {
        return MyBatis3Utils.insert(this::insert, row, loginUser, c ->
            c.map(id).toPropertyWhenPresent("id", row::getId)
            .map(name).toPropertyWhenPresent("name", row::getName)
            .map(email).toPropertyWhenPresent("email", row::getEmail)
            .map(password).toPropertyWhenPresent("password", row::getPassword)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<LoginUser> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, loginUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<LoginUser> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, loginUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<LoginUser> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, loginUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<LoginUser> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, loginUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(LoginUser row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(row::getId)
                .set(name).equalTo(row::getName)
                .set(email).equalTo(row::getEmail)
                .set(password).equalTo(row::getPassword);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(LoginUser row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(row::getId)
                .set(name).equalToWhenPresent(row::getName)
                .set(email).equalToWhenPresent(row::getEmail)
                .set(password).equalToWhenPresent(row::getPassword);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(LoginUser row) {
        return update(c ->
            c.set(name).equalTo(row::getName)
            .set(email).equalTo(row::getEmail)
            .set(password).equalTo(row::getPassword)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(LoginUser row) {
        return update(c ->
            c.set(name).equalToWhenPresent(row::getName)
            .set(email).equalToWhenPresent(row::getEmail)
            .set(password).equalToWhenPresent(row::getPassword)
            .where(id, isEqualTo(row::getId))
        );
    }
}