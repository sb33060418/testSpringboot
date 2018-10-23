package com.sunbin.springboot.mapper;

import com.sunbin.springboot.pojo.User;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    @Select("select * from user order by id")
    List<User> findAll();

    @Select("select * from user where id = #{id}")
    User findById(int id);
    
    @Insert("insert into user(id,name) values (#{id}, #{name})")
    int saveUser(User user);
    
    @Update("update user set name = #{name} where id = #{id}")
    int updateUser(User user);
    
    @Delete("delete from user where id = #{id}")
    int deleteById(int id);

    @Select("select * from user order by id")
    List<Map> findAllMap();
    
    List<User> findAllInXml();
}
