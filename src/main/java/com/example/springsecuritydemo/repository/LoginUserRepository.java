package com.example.springsecuritydemo.repository;

import com.example.springsecuritydemo.mbgenerate.crud.CustomLoginUserMapper;
import com.example.springsecuritydemo.mbgenerate.crud.LoginUserDynamicSqlSupport;
import com.example.springsecuritydemo.mbgenerate.crud.LoginUserMapper;
import com.example.springsecuritydemo.mbgenerate.entity.LoginUser;
import com.example.springsecuritydemo.mbgenerate.entity.LoginUserRolesEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class LoginUserRepository {
    private final LoginUserMapper loginUserMapper;
    private final CustomLoginUserMapper customLoginUserMapper;

    public Optional<LoginUser> findById(final int id) {
        return loginUserMapper.selectByPrimaryKey(id);
    }

    public Optional<LoginUserRolesEntity> selectLoginUserRolesEntityById(final int userId) {
        return customLoginUserMapper.selectLoginUserRolesEntity(userId);
    }

    public void simpleWhere() {
        log.info("----- simple where -----");
        loginUserMapper.select(c -> c.where(LoginUserDynamicSqlSupport.id, SqlBuilder.isEqualTo(1)))
                .forEach(e -> log.info("{}, {}, {}, {}", e.getId(), e.getName(), e.getEmail(), e.getPassword()));
    }

//    public void select(final int userId) {
//        loginUserMapper.select(c -> c.join())
//    }

    public void complexWhere() {
        log.info("----- complex where -----");
        loginUserMapper.select(c -> c.where(LoginUserDynamicSqlSupport.email, SqlBuilder.isLike("%@example.com"))
                .and(LoginUserDynamicSqlSupport.name, SqlBuilder.isLike("管理%"))
        ).forEach(e -> log.info("{}, {}, {}, {}", e.getId(), e.getName(), e.getEmail(), e.getPassword()));
    }

    public void useCustomLoginUserMapper() {
        log.info("----- use custom mapper -----");
        customLoginUserMapper.selectAll()
                .forEach(e -> log.info("{}, {}, {}, {}", e.getId(), e.getName(), e.getEmail(), e.getPassword()));
        customLoginUserMapper.selectAllBySqlFile()
                .forEach(e -> log.info("{}, {}, {}, {}", e.getId(), e.getName(), e.getEmail(), e.getPassword()));
    }

//    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
//    @Results(id = "AnimalDataResult", value = {
//            @Result(column = "id", property = "id", id = true),
//            @Result(column = "animal_name", property = "animalName"),
//            @Result(column = "brain_weight", property = "brainWeight"),
//            @Result(column = "body_weight", property = "bodyWeight")
//    })
//    List<AnimalData> selectMany(final SelectStatementProvider selectStatement) {
//    }
//
//    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
//    @ResultMap("AnimalDataResult")
//    AnimalData selectOne(SelectStatementProvider selectStatement);
}
