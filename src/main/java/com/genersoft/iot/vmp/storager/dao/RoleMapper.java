package com.genersoft.iot.vmp.storager.dao;

import com.genersoft.iot.vmp.storager.dao.dto.Role;
import com.genersoft.iot.vmp.storager.dao.dto.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleMapper {

    @Insert("INSERT INTO t_role (name, authority, createTime, updateTime) VALUES" +
            "('${name}', '${authority}', '${createTime}', '${updateTime}')")
    int add(Role role);

    @Update(value = {" <script>" +
            "UPDATE t_role " +
            "SET updateTime='${updateTime}' " +
            "<if test=\"name != null\">, name='${name}'</if>" +
            "<if test=\"authority != null\">, authority='${authority}'</if>" +
            "WHERE id != 1 and id=#{id}" +
            " </script>"})
    int update(Role role);

    @Delete("DELETE FROM t_role WHERE  id != 1 and id=#{id}")
    int delete(int id);

    @Select("select * FROM t_role WHERE id=#{id}")
    Role selectById(int id);

    @Select("select * FROM t_role")
    List<Role> selectAll();
}
