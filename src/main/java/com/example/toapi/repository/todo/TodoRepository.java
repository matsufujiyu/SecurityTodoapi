package com.example.toapi.repository.todo;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TodoRepository {

    //GET ひとつだけ
    @Select("SELECT * FROM TODOLIST WHERE id = #{id}")
    Optional<TodoRecord> select(long id);

    //GET 一覧
    @Select("SELECT * FROM TODOLIST LIMIT #{limit} OFFSET #{offset}")
    List<TodoRecord> selectList(int limit, long offset);

    //POST
    @Options(useGeneratedKeys = true, keyProperty = "id")//idが自動で割り振られる
    @Insert("INSERT INTO TODOLIST (title,status,details) VALUES (#{title},#{status},#{details})")
    void insert(TodoRecord record);


    @Update("UPDATE TODOLIST SET title = #{title},status = #{status},details = #{details} WHERE id = #{id}")
    void update(TodoRecord todoRecord);

    @Delete("DELETE FROM TODOLIST WHERE id = #{id}")
    void delete(Long id);
}
