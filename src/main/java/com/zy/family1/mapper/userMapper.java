package com.zy.family1.mapper;

import com.zy.family1.entity.user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Mapper
@Component
public interface userMapper {

    //用户登录
    user userLogin(@Param("username") String username, @Param("password") String password);

    //注册新用户(
    int addUser(@Param("username") String username, @Param("password") String password, @Param("email") String email);

    //用户信息更新
    int modifyUser(@Param("id")int id,@Param("username")String username, @Param("password") String password, @Param("email") String email);

    //查询用户列表
    List<Map<String,Object>> queryAllUser();

    int deleteByPrimaryKey(String id);

    int insert(user record);

    int insertSelective(user record);

    user selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(user record);

    int updateByPrimaryKey(user record);
}