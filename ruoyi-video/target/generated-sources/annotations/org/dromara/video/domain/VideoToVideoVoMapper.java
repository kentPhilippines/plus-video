package org.dromara.video.domain;

import io.github.linpeilie.AutoMapperConfig__15;
import io.github.linpeilie.BaseMapper;
import org.dromara.video.domain.bo.VideoBoToVideoMapper;
import org.dromara.video.domain.vo.VideoVo;
import org.dromara.video.domain.vo.VideoVoToVideoMapper;
import org.mapstruct.Mapper;

@Mapper(
    config = AutoMapperConfig__15.class,
    uses = {VideoBoToVideoMapper.class,VideoVoToVideoMapper.class},
    imports = {}
)
public interface VideoToVideoVoMapper extends BaseMapper<Video, VideoVo> {
}
