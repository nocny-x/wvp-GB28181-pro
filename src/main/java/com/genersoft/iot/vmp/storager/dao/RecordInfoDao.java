package com.genersoft.iot.vmp.storager.dao;

import com.genersoft.iot.vmp.storager.dao.dto.RecordInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecordInfoDao {

    @Insert("INSERT INTO t_recordInfo (app, stream, mediaServerId, createTime, type, deviceId, channelId, name) VALUES" +
            "('${app}', '${stream}', '${mediaServerId}', datetime('now','localtime')), '${type}', '${deviceId}', '${channelId}', '${name}'")
    int add(RecordInfo recordInfo);

    @Delete("DELETE FROM t_user WHERE createTime < '${beforeTime}'")
    int deleteBefore(String beforeTime);

    @Select("select * FROM t_recordInfo")
    List<RecordInfo> selectAll();
}
