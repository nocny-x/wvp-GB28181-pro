package com.genersoft.iot.vmp.storager.dao;

import com.genersoft.iot.vmp.storager.dao.dto.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @Insert("INSERT INTO t_user (username, password, roleId, createTime, updateTime) VALUES" +
            "('${username}', '${password}', '${role.id}', '${createTime}', '${updateTime}')")
    int add(User user);

    @Update(value = {" <script>" +
            "UPDATE t_user " +
            "SET updateTime='${updateTime}' " +
            "<if test=\"role != null\">, roleId='${role.id}'</if>" +
            "<if test=\"password != null\">, password='${password}'</if>" +
            "<if test=\"username != null\">, username='${username}'</if>" +
            "WHERE id=#{id}" +
            " </script>"})
    int update(User user);

    @Delete("DELETE FROM t_user WHERE id != 1 and id=#{id}")
    int delete(int id);

    @Select("select u.*, r.id roleID, r.name roleName, r.authority roleAuthority, r.createTime roleCreateTime, r.updateTime roleUpdateTime" +
            " FROM t_user u inner join t_role r on u.roleId=r.id and u.username=#{username} AND u.password=#{password}")
    @Results(id = "roleMap", value = {
            @Result(column = "roleID", property = "role.id"),
            @Result(column = "roleName", property = "role.name"),
            @Result(column = "roleAuthority", property = "role.authority"),
            @Result(column = "roleCreateTime", property = "role.createTime"),
            @Result(column = "roleUpdateTime", property = "role.updateTime")
    })
    User select(String username, String password);

    @Select("select u.*, r.id roleID, r.name roleName, r.authority roleAuthority, r.createTime roleCreateTime, r.updateTime roleUpdateTime FROM t_user u inner join t_role r on u.roleId=r.id and u.id=#{id}")
    @ResultMap(value="roleMap")
    User selectById(int id);

    @Select("select u.*, r.id, roleID, r.name, r.name roleName, r.authority  roleAuthority, r.createTime roleCreateTime, r.updateTime roleUpdateTime " +
            " FROM t_user u" +
            " inner join t_role r on u.roleId = r.id and username=#{username}")
    @ResultMap(value="roleMap")
    User getUserByUsername(String username);

    @Select("select u.*, r.id roleID, r.name roleName, r.authority roleAuthority, r.createTime roleCreateTime, r.updateTime roleUpdateTime FROM t_user u inner join t_role r on user.roleId=role.id")
    @ResultMap(value="roleMap")
    List<User> selectAll();
}
