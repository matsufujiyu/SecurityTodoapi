package com.example.toapi.auth;

import com.example.todoapi.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserRepositoriy {

    @Select("select * from users where userId = #{userId}")
    Optional<Usermodel> findByUsername(String userId);

    @Select("select * from users")
    List<Usermodel> findAll();

    @Insert("insert into users(userId,email,password,authority) values (#{userId},#{email},#{password},#{authority})")
    void insert(String userId , String email , String password,String authority);

}
