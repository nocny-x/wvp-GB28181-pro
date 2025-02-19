package com.genersoft.iot.vmp.storager.dao;

import com.genersoft.iot.vmp.gb28181.bean.PlatformGbStream;
import com.genersoft.iot.vmp.media.zlm.dto.StreamProxyItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface PlatformGbStreamMapper {

    @Insert("INSERT INTO t_platform_gb_stream (app, stream, platformId) VALUES" +
            "('${app}', '${stream}', '${platformId}')")
    int add(PlatformGbStream platformGbStream);

    @Delete("DELETE FROM t_platform_gb_stream WHERE app=#{app} AND stream=#{stream}")
    int delByAppAndStream(String app, String stream);

    @Delete("DELETE FROM t_platform_gb_stream WHERE platformId=#{platformId}")
    int delByPlatformId(String platformId);

    @Select("SELECT * FROM t_platform_gb_stream WHERE app=#{app} AND stream=#{stream}")
    List<StreamProxyItem> selectByAppAndStream(String app, String stream);
}
